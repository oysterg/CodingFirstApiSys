package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.interceptor.CaptchaRequired;
import team.fjut.cf.component.interceptor.LoginRequired;
import team.fjut.cf.component.interceptor.PrivateRequired;
import team.fjut.cf.component.jwt.JwtTokenManager;
import team.fjut.cf.component.token.TokenModel;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.UserAuth;
import team.fjut.cf.pojo.po.UserBaseInfo;
import team.fjut.cf.pojo.po.UserCustomInfo;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.pojo.vo.UserCustomInfoVO;
import team.fjut.cf.service.*;
import team.fjut.cf.util.JsonFileUtils;
import team.fjut.cf.util.IpUtils;
import team.fjut.cf.util.UUIDUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author axiang [2019/10/11]
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    UserCheckInService userCheckInService;

    @Autowired
    JudgeStatusService judgeStatusService;

    @Autowired
    BorderHonorRankService borderHonorRankService;

    @Autowired
    ChallengeBlockService challengeBlockService;

    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    UserCaptchaService userCaptchaService;

    /**
     * 获取游客token
     *
     * @param request
     * @return
     */
    @PostMapping("/guest/token")
    public ResultJson requireGuestToken(HttpServletRequest request) {

        ResultJson resultJson = new ResultJson();
        TokenModel tokenModel = new TokenModel();
        tokenModel.setIp(IpUtils.getClientIpAddress(request));
        tokenModel.setUsername(UUIDUtils.getUUID32());
        tokenModel.setRole("Guest");
        String token = jwtTokenManager.createToken(tokenModel);
        resultJson.addInfo(token);
        return resultJson;
    }

    @CaptchaRequired
    @PostMapping("/login")
    public ResultJson userLogin(HttpServletRequest request,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("captcha") String captcha) {
        ResultJson resultJson = new ResultJson();
        Date currentDate = new Date();


        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "用户名或者密码为空！");
            return resultJson;
        }
        // 用户名不存在
        if (!userInfoService.isUsernameExist(username)) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "登录失败！用户不存在！");
            return resultJson;
        }
        // 查询账号是否激活
        boolean isUserLocked = userAuthService.isUserLocked(username);
        if (isUserLocked) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "您的账号还未激活，请到邮箱中点击激活！");
            return resultJson;
        }
        // 查询登录权限的解锁时间
        Date unlockTime = userAuthService.selectUnlockTime(username);
        // 如果当前时间小于解锁时间，则表示账号还在锁定期，无法登录
        if (0 > currentDate.compareTo(unlockTime)) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "您的账号已暂时被锁定，请稍后登录。如有疑问，请联系管理员");
            return resultJson;
        }
        if (userInfoService.userLogin(username, password)) {
            resultJson.setStatus(ResultCode.REQUIRED_SUCCESS, "登录成功！");
            UserCustomInfoVO userCustomInfoVO = userInfoService.selectUserCustomInfo(username);
            TokenModel tokenModel = new TokenModel();
            tokenModel.setIp(IpUtils.getClientIpAddress(request));
            tokenModel.setUsername(username);
            tokenModel.setRole("User");
            String token = jwtTokenManager.createToken(tokenModel);
            resultJson.addInfo(username);
            resultJson.addInfo(token);
            resultJson.addInfo(userCustomInfoVO);
        } else {
            Integer attemptCount = userAuthService.selectAttemptNumber(username);
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "登录失败！账号或密码不正确！您还有 " + (Math.max(5 - attemptCount, 0)) + "次机会");
        }
        return resultJson;
    }


    @CaptchaRequired
    @PostMapping("/register")
    public ResultJson userRegister(HttpServletRequest request,
                                   @RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("nickname") String nickname,
                                   @RequestParam("gender") Integer gender,
                                   @RequestParam("email") String email,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("motto") String motto,
                                   @RequestParam("avatarUrl") String avatarUrl,
                                   @RequestParam("captcha") String captcha) {
        ResultJson resultJson = new ResultJson();

        Boolean isExist = userInfoService.isUsernameExist(username);
        if (isExist) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "注册的用户已存在！");
            return resultJson;
        }
        boolean isRestrict = JsonFileUtils.isUsernameRestrict(username);
        if (isRestrict) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "用户名中含有关键字，请选择新的用户名后重试");
            return resultJson;
        }
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setUsername(username);
        userBaseInfo.setGender(gender);
        userBaseInfo.setEmail(email);
        userBaseInfo.setPhone(phone);
        userBaseInfo.setMotto(motto);
        userBaseInfo.setRegisterTime(new Date());
        userBaseInfo.setRating(0);
        userBaseInfo.setAcb(0);
        UserAuth userAuth = new UserAuth();
        userAuth.setPassword(password);
        UserCustomInfo userCustomInfo = new UserCustomInfo();
        userCustomInfo.setAvatarUrl(avatarUrl);
        userCustomInfo.setNickname(nickname);
        Boolean ans = userInfoService.registerUser(userBaseInfo, userAuth, userCustomInfo);

        if (ans) {
            resultJson.setStatus(ResultCode.REQUIRED_SUCCESS, "用户注册成功！");
            //// 插入挑战模式解锁记录
            //ChallengeUserOpenBlockPO challengeUserOpenBlockPO = new ChallengeUserOpenBlockPO();
            //challengeUserOpenBlockPO.setUsername(username);
            //challengeUserOpenBlockPO.setBlockId(1);
            //challengeUserOpenBlockPO.setUnlockTime(new Date());
            //challengeBlockService.unlockBlock(challengeUserOpenBlockPO);
        } else {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "用户注册失败！");
        }
        return resultJson;
    }

    @PrivateRequired
    @PostMapping("/logout")
    public ResultJson userLogOut(String username) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        jwtTokenManager.deleteToken(username);
        return resultJson;
    }

    @LoginRequired
    @PostMapping("/info")
    public ResultJson getUserInfo(@RequestParam("username") String username) {
        ResultJson resultJson = new ResultJson();
        UserBaseInfo userBaseInfoVO = userInfoService.selectByUsername(username);
        UserCustomInfoVO userCustomInfoVO = userInfoService.selectUserCustomInfo(username);
        Integer totalSubmit = judgeStatusService.selectCountByUsername(username);
        resultJson.addInfo(userBaseInfoVO);
        resultJson.addInfo(userCustomInfoVO);
        resultJson.addInfo(totalSubmit);
        return resultJson;
    }


    @PostMapping("/info/custom")
    public ResultJson getUserCustomerInfo(@RequestParam("username") String username) {
        ResultJson resultJson = new ResultJson();
        UserCustomInfoVO userCustomInfoVO = userInfoService.selectUserCustomInfo(username);
        resultJson.addInfo(userCustomInfoVO);
        return resultJson;
    }

    @LoginRequired
    @PostMapping("/award")
    public ResultJson getUserAwardList(@RequestParam("username") String username) {
        ResultJson resultJson = new ResultJson();
        List<String> awardStr = borderHonorRankService.selectByUsername(username);
        resultJson.addInfo(awardStr);
        return resultJson;
    }

}





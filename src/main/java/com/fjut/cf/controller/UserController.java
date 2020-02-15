package com.fjut.cf.controller;

import com.fjut.cf.component.interceptor.LoginRequired;
import com.fjut.cf.component.interceptor.PrivateRequired;
import com.fjut.cf.component.token.TokenManager;
import com.fjut.cf.component.token.TokenModel;
import com.fjut.cf.pojo.enums.ResultJsonCode;
import com.fjut.cf.pojo.po.ChallengeUserOpenBlockPO;
import com.fjut.cf.pojo.po.UserBaseInfoPO;
import com.fjut.cf.pojo.vo.ResultJsonVO;
import com.fjut.cf.pojo.vo.UserCustomInfoVO;
import com.fjut.cf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    TokenManager tokenManager;

    @PostMapping("/login")
    public ResultJsonVO postUserLogin(@RequestParam("username") String username,
                                      @RequestParam("password") String password) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        Date currentDate = new Date();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "用户名或者密码为空！");
            return resultJsonVO;
        }
        // 用户名不存在
        if (!userInfoService.selectExistByUsername(username)) {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "登录失败！用户不存在！");
            return resultJsonVO;
        }
        // 查询登录权限的解锁时间
        Date unlockTime = userInfoService.selectUnlockTimeByUsername(username);
        // 如果当前时间小于解锁时间，则表示账号还在锁定期，无法登录
        if (0 > currentDate.compareTo(unlockTime)) {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "您的账号已暂时被锁定，请稍后登录。如有疑问，请联系管理员");
            return resultJsonVO;
        }
        Integer integer = userAuthService.selectLockedByUsername(username);
        if (integer == 1) {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "您的账号还未激活，请到邮箱中点击激活！");
            return resultJsonVO;
        }
        if (userInfoService.login(username, password)) {
            resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS, "登录成功！");
            UserCustomInfoVO userCustomInfoVO = userInfoService.selectUserCustomInfoByUsername(username);
            TokenModel tokenModel = tokenManager.createToken(username);
            String auth = tokenManager.createAuth(tokenModel);
            resultJsonVO.addInfo(username);
            resultJsonVO.addInfo(auth);
            resultJsonVO.addInfo(userCustomInfoVO);
        } else {
            Integer attemptCount = userInfoService.selectAttemptNumberByUsername(username);
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "登录失败！账号或密码不正确！您还有 " + (Math.max(5 - attemptCount, 0)) + "次机会");
        }
        return resultJsonVO;
    }

    @PostMapping("/register")
    public ResultJsonVO postUserRegister(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         @RequestParam("nick") String nick,
                                         @RequestParam("gender") Integer gender,
                                         @RequestParam("email") String email,
                                         @RequestParam("phone") String phone,
                                         @RequestParam("motto") String motto,
                                         @RequestParam(value = "school", required = false) String school,
                                         @RequestParam(value = "faculty", required = false) String faculty,
                                         @RequestParam(value = "major", required = false) String major,
                                         @RequestParam(value = "cla", required = false) String cla,
                                         @RequestParam(value = "studentId", required = false) String studentId,
                                         @RequestParam(value = "graduationYear", required = false) String graduationYear,
                                         @RequestParam(value = "avatarUrl", required = false) String avatarUrl) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        Boolean isExist = userInfoService.selectExistByUsername(username);
        if (isExist) {
            resultJsonVO.setStatus(ResultJsonCode.SYSTEM_ERROR, "注册的用户已存在！");
            return resultJsonVO;
        }
        UserBaseInfoPO userBaseInfo = new UserBaseInfoPO();
        userBaseInfo.setUsername(username);
        userBaseInfo.setNick(nick);
        userBaseInfo.setGender(gender);
        userBaseInfo.setEmail(email);
        userBaseInfo.setPhone(phone);
        userBaseInfo.setMotto(motto);
        userBaseInfo.setRegisterTime(new Date());
        userBaseInfo.setSchool(school);
        userBaseInfo.setFaculty(faculty);
        userBaseInfo.setMajor(major);
        userBaseInfo.setCla(cla);
        userBaseInfo.setStudentId(studentId);
        userBaseInfo.setGraduationYear(graduationYear);
        Boolean ans = userInfoService.registerUser(userBaseInfo, password, avatarUrl);
        if (ans) {
            resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS, "用户注册成功！");
            // 插入挑战模式解锁记录
            ChallengeUserOpenBlockPO challengeUserOpenBlockPO = new ChallengeUserOpenBlockPO();
            challengeUserOpenBlockPO.setUsername(username);
            challengeUserOpenBlockPO.setBlockId(1);
            challengeUserOpenBlockPO.setUnlockTime(new Date());
            challengeBlockService.unlockBlock(challengeUserOpenBlockPO);
        } else {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "用户注册失败！");
        }
        return resultJsonVO;
    }

    @PrivateRequired
    @PostMapping("/logout")
    public ResultJsonVO postUserLogOut(@RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        tokenManager.deleteToken(username);
        return resultJsonVO;
    }

    @LoginRequired
    @GetMapping("/info")
    public ResultJsonVO getUserInfoByUsername(@RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        UserBaseInfoPO userBaseInfoVO = userInfoService.selectByUsername(username);
        UserCustomInfoVO userCustomInfoVO = userInfoService.selectUserCustomInfoByUsername(username);
        Integer totalSubmit = judgeStatusService.selectCountByUsername(username);
        resultJsonVO.addInfo(userBaseInfoVO);
        resultJsonVO.addInfo(userCustomInfoVO);
        resultJsonVO.addInfo(totalSubmit);
        return resultJsonVO;
    }

    @LoginRequired
    @GetMapping("/award")
    public ResultJsonVO getUserAwardListByUsername(@RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        List<String> awardStr = borderHonorRankService.selectByUsername(username);
        resultJsonVO.addInfo(awardStr);
        return resultJsonVO;
    }

}





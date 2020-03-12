package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.fjut.cf.component.email.EmailTool;
import team.fjut.cf.mapper.*;
import team.fjut.cf.pojo.po.*;
import team.fjut.cf.pojo.vo.UserAcNumBorderVO;
import team.fjut.cf.pojo.vo.UserAcbBorderVO;
import team.fjut.cf.pojo.vo.UserCustomInfoVO;
import team.fjut.cf.pojo.vo.UserRatingBorderVO;
import team.fjut.cf.service.UserInfoService;
import team.fjut.cf.service.UserMessageService;
import team.fjut.cf.util.SHAUtils;
import team.fjut.cf.util.UUIDUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author axiang [2019/10/11]
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserBaseInfoMapper userBaseInfoMapper;

    @Autowired
    UserAuthMapper userAuthMapper;

    @Autowired
    UserCustomInfoMapper userCustomInfoMapper;

    @Autowired
    UserCheckInMapper userCheckInMapper;

    @Autowired
    UserSealMapper userSealMapper;

    @Autowired
    UserTitleMapper userTitleMapper;

    @Autowired
    UserMessageService userMessageService;

    @Autowired
    EmailTool emailTool;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean registerUser(UserBaseInfo userBaseInfo, UserAuth userAuth, UserCustomInfo userCustomInfo) {
        // 插入用户基本信息
        int ans1 = userBaseInfoMapper.insert(userBaseInfo);
        // 插入用户权限信息
        Date currentTime = new Date();
        // 得到随机盐值
        String salt = UUIDUtils.getUUID32();
        // 加盐密码
        String newPassword = salt + userAuth.getPassword();
        // 对加盐密码使用SHA1加密
        String encryptedPwd = SHAUtils.SHA1(newPassword);
        userAuth.setUsername(userBaseInfo.getUsername());
        userAuth.setSalt(salt);
        userAuth.setPassword(encryptedPwd);
        userAuth.setAttemptLoginFailCount(0);
        // FIXME: 新注册用户进行锁定，但由于邮箱服务问题暂时跳过该逻辑
        userAuth.setLocked(0);
        userAuth.setUnlockTime(currentTime);
        userAuth.setLastLoginTime(currentTime);
        int ans2 = userAuthMapper.insert(userAuth);
        // 插入头像信息
        userCustomInfo.setUsername(userBaseInfo.getUsername());
        int ans3 = userCustomInfoMapper.insert(userCustomInfo);
        // 发送激活确认邮件
        //SendEmailBO sendEmailBO = new SendEmailBO();
        //sendEmailBO.setTo(userBaseInfoPO.getEmail());
        //sendEmailBO.setSubject("一码当先acm.fjutcoder.com注册激活邮件");
        //sendEmailBO.setText(userBaseInfoPO.getUsername() + "您好：\n"+"欢迎注册一码当先，请点击以下链接激活账号："
        //+"http://xxxxxxxx");
        //emailTool.sendEmail(sendEmailBO);
        // 发送注册成功站内消息
        // FIXME: 站内消息表更改
        //UserMessage userMessage = new UserMessage();
        //userMessage.setUsername(userBaseInfo.getUsername());
        //userMessage.setTitle("欢迎注册一码当先在线编程系统");
        //userMessage.setStatus(0);
        //userMessage.setText("欢迎注册一码当先在线编程系统，目前我们正在进行内测，功能并未完全开发完毕，如果遇到错误，请通过页面最下方的bug反馈进行反馈哦");
        //userMessage.setTime(new Date());
        //userMessageService.insert(userMessage);
        return ans1 == 1 && ans2 == 1 && ans3 == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean userLogin(String username, String password) {
        // 最大登录尝试次数
        int maxAttemptTimes = 5;
        Example example = new Example(UserAuth.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        // 拿到权限表的内容
        UserAuth userAuth = userAuthMapper.selectOneByExample(example);
        // 取出数据库中盐值
        String salt = userAuth.getSalt();
        String newPwd = salt + password;
        // 对明文密码和盐值做加密
        String encryptPwd = SHAUtils.SHA1(newPwd);
        criteria.andEqualTo("password", encryptPwd);
        // 查询用户名密码是否对应上
        int ans = userAuthMapper.selectCountByExample(example);
        // 重置example为只查询username
        example.clear();
        example.createCriteria().andEqualTo("username", username);
        // 登录成功
        if (1 == ans) {
            UserAuth newUserAuth = new UserAuth();
            // 重置尝试失败次数为0
            newUserAuth.setAttemptLoginFailCount(0);
            // 更新最后登录时间
            newUserAuth.setLastLoginTime(new Date());
            userAuthMapper.updateByExampleSelective(newUserAuth, example);
            return true;
        }
        // 登录失败
        else {
            // 拿到该登录失败用户的表记录
            UserAuth temp = userAuthMapper.selectOneByExample(example);
            // 更新登录失败次数+1
            temp.setAttemptLoginFailCount(temp.getAttemptLoginFailCount() + 1);
            // 更新回去
            userAuthMapper.updateByExampleSelective(temp, example);
            // 如果查询上次登录次数即将超限，则还需要设置超时时间
            if (temp.getAttemptLoginFailCount() >= maxAttemptTimes) {
                long timeLaterFiveMin = System.currentTimeMillis() + (60 * 5 * 1000);
                temp.setUnlockTime(new Date(timeLaterFiveMin));
                userAuthMapper.updateByExampleSelective(temp, example);
            }
            return false;
        }
    }


    @Override
    public Boolean isUsernameExist(String username) {
        Example example = new Example(UserAuth.class);
        example.createCriteria().andEqualTo("username", username);
        int num1 = userAuthMapper.selectCountByExample(example);
        System.out.println(num1);
        Example example1 = new Example(UserBaseInfo.class);
        example1.createCriteria().andEqualTo("username", username);
        int num2 = userBaseInfoMapper.selectCountByExample(example1);
        System.out.println(num2);
        return (num1 == 1) && (num2 == 1);
    }




    @Override
    public UserBaseInfo selectByUsername(String username) {
        UserBaseInfo userBaseInfo = userBaseInfoMapper.selectByUsername(username);
        return userBaseInfo;
    }

    @Override
    public UserCustomInfoVO selectUserCustomInfo(String username) {
        UserCustomInfoVO result = new UserCustomInfoVO();
        Example example = new Example(UserCustomInfo.class);
        example.createCriteria().andEqualTo("username", username);
        UserCustomInfo userCustomInfo = userCustomInfoMapper.selectOneByExample(example);
        // 如果为空返回空内容
        if (null == userCustomInfo) {
            return result;
        }
        result.setNickname(userCustomInfo.getNickname());
        result.setId(userCustomInfo.getId());
        result.setUsername(userCustomInfo.getUsername());
        result.setAvatarUrl(userCustomInfo.getAvatarUrl());
        if (userCustomInfo.getAdjectiveId() != null) {
            UserTitle userAdjTitle = userTitleMapper.selectByPrimaryKey(userCustomInfo.getAdjectiveId());
            result.setAdjectiveTitle(userAdjTitle.getName());
        }
        if (userCustomInfo.getArticleId() != null) {
            UserTitle userArtTitle = userTitleMapper.selectByPrimaryKey(userCustomInfo.getArticleId());
            result.setArticleTitle(userArtTitle.getName());
        }
        if (userCustomInfo.getSealId() != null) {
            UserSeal userSeal = userSealMapper.selectByPrimaryKey(userCustomInfo.getSealId());
            result.setSealUrl(userSeal.getPictureUrl());
        }
        return result;
    }

    @Override
    public List<UserAcbBorderVO> selectAcbBorder(int pageNum, int pageSize) {
        List<UserAcbBorderVO> userAcbBorderVOS = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<UserBaseInfo> userBaseInfos = userBaseInfoMapper.allAcbTop();
        for (UserBaseInfo userBase : userBaseInfos) {
            UserAcbBorderVO userAcbBorderVO = new UserAcbBorderVO();
            userAcbBorderVO.setUsername(userBase.getUsername());
            // FIXME: 昵称换表了
            //userAcbBorderVO.setNick(userBase.getNick());
            userAcbBorderVO.setAcb(userBase.getAcb());
            userAcbBorderVOS.add(userAcbBorderVO);
        }
        return userAcbBorderVOS;
    }

    @Override
    public List<UserAcNumBorderVO> selectAcNumBorder(int pageNum, int pageSize) {
        List<UserAcNumBorderVO> userAcNumBorderVOS = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<UserBaseInfo> userBaseInfos = userBaseInfoMapper.allAcNumTop();
        for (UserBaseInfo userBase : userBaseInfos) {
            UserAcNumBorderVO userAcbBorderVO = new UserAcNumBorderVO();
            userAcbBorderVO.setUsername(userBase.getUsername());
            // FIXME: 昵称换表了
            //userAcbBorderVO.setNick(userBase.getNick());
            userAcbBorderVO.setAcNum(userBase.getAcNum());
            userAcNumBorderVOS.add(userAcbBorderVO);
        }
        return userAcNumBorderVOS;
    }

    @Override
    public List<UserRatingBorderVO> selectRatingBorder(int pageNum, int pageSize) {
        List<UserRatingBorderVO> userRatingBorderVOS = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<UserBaseInfo> userBaseInfos = userBaseInfoMapper.allRatingTop();
        for (UserBaseInfo userBase : userBaseInfos) {
            UserRatingBorderVO userRatingBorderVO = new UserRatingBorderVO();
            userRatingBorderVO.setUsername(userBase.getUsername());
            // FIXME: 昵称换表了
            //userRatingBorderVO.setNick(userBase.getNick());
            userRatingBorderVO.setRating(userBase.getRating());
            userRatingBorderVOS.add(userRatingBorderVO);
        }
        return userRatingBorderVOS;
    }

}

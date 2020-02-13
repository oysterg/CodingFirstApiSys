package com.fjut.cf.service.impl;

import com.fjut.cf.component.email.EmailTool;
import com.fjut.cf.mapper.*;
import com.fjut.cf.pojo.po.*;
import com.fjut.cf.pojo.vo.*;
import com.fjut.cf.service.UserInfoService;
import com.fjut.cf.service.UserMessageService;
import com.fjut.cf.util.SHAUtils;
import com.fjut.cf.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Boolean registerUser(UserBaseInfoPO userBaseInfoPO, String password, String avatarUrl) {
        // 插入用户基本信息
        int ans1 = userBaseInfoMapper.insert(userBaseInfoPO);
        // 插入用户权限信息
        UserAuthPO userAuthPO = new UserAuthPO();
        Date currentTime = new Date();
        // 得到随机盐值
        String salt = UUIDUtils.getUUID32();
        // 加盐密码
        String newPassword = salt + password;
        // 对加盐密码使用SHA1加密
        String encryptedPwd = SHAUtils.SHA1(newPassword);
        userAuthPO.setUsername(userBaseInfoPO.getUsername());
        userAuthPO.setSalt(salt);
        userAuthPO.setPassword(encryptedPwd);
        userAuthPO.setAttemptLoginFailCount(0);
        // FIXME: 新注册用户进行锁定，但由于邮箱服务问题暂时跳过该逻辑
        userAuthPO.setLocked(0);
        userAuthPO.setUnlockTime(currentTime);
        userAuthPO.setLastLoginTime(currentTime);
        int ans2 = userAuthMapper.insert(userAuthPO);
        // 插入头像信息
        UserCustomInfoPO userCustomInfoPO = new UserCustomInfoPO();
        userCustomInfoPO.setUsername(userBaseInfoPO.getUsername());
        userCustomInfoPO.setAvatarUrl(avatarUrl);
        Integer ans3 = userCustomInfoMapper.insert(userCustomInfoPO);
        // 发送激活确认邮件
        //SendEmailBO sendEmailBO = new SendEmailBO();
        //sendEmailBO.setTo(userBaseInfoPO.getEmail());
        //sendEmailBO.setSubject("一码当先acm.fjutcoder.com注册激活邮件");
        //sendEmailBO.setText(userBaseInfoPO.getUsername() + "您好：\n"+"欢迎注册一码当先，请点击以下链接激活账号："
        //+"http://xxxxxxxx");
        //emailTool.sendEmail(sendEmailBO);
        // 发送注册成功站内消息
        UserMessagePO userMessagePO = new UserMessagePO();
        userMessagePO.setUsername(userBaseInfoPO.getUsername());
        userMessagePO.setTitle("欢迎注册一码当先在线编程系统");
        userMessagePO.setStatus(0);
        userMessagePO.setText("欢迎注册一码当先在线编程系统，目前我们正在进行内测，功能并未完全开发完毕，如果遇到错误，请通过页面最下方的bug反馈进行反馈哦");
        userMessagePO.setTime(new Date());
        userMessageService.insert(userMessagePO);
        return ans1 == 1 && ans2 == 1 && ans3 == 1;
    }

    @Override
    public Boolean login(String username, String password) {
        // 最大登录尝试次数
        int maxAttemptTimes = 5;
        // 取出数据库中盐值
        String salt = userAuthMapper.selectSaltByUsername(username);
        String newPwd = salt + password;
        // 对明文密码和盐值做加密
        String encryptPwd = SHAUtils.SHA1(newPwd);
        // 查询用户名密码是否对应上
        Integer ans = userAuthMapper.selectByUsernameAndPassword(username, encryptPwd);
        // 登录成功
        if (1 == ans) {
            // 重置尝试失败次数为0
            userAuthMapper.updateAttemptFailSetZeroByUsername(username);
            return true;
        }
        // 登录失败
        else {
            //更新登录失败次数+1
            userAuthMapper.updateAttemptFailByUsername(username, 1);
            //查询登录失败次数
            Integer failLoginCount = userAuthMapper.selectAttemptNumberByUsername(username);
            //超过最大登录失败次数，则账号锁定五分钟
            if (failLoginCount >= maxAttemptTimes) {
                //设置时间为现在时间的五分钟后
                long timeLaterFiveMin = System.currentTimeMillis() + (60 * 5 * 1000);
                userAuthMapper.updateUnlockTimeByUsername(new Date(timeLaterFiveMin), username);
            }
            return false;
        }
    }

    @Override
    public Date selectUnlockTimeByUsername(String username) {
        return userAuthMapper.selectUnlockTimeByUsername(username);
    }

    @Override
    public Boolean selectExistByUsername(String username) {
        Integer num1 = userAuthMapper.selectCountByUsername(username);
        Integer num2 = userBaseInfoMapper.selectCountByUsername(username);
        return (num1 == 1) && (num2 == 1);
    }

    @Override
    public Integer selectAttemptNumberByUsername(String username) {
        return userAuthMapper.selectAttemptNumberByUsername(username);
    }

    @Override
    public List<UserBaseInfoPO> pagesUserBaseInfo(int startIndex, int pageSize) {
        return userBaseInfoMapper.pages(startIndex, pageSize);
    }

    @Override
    public UserBaseInfoPO selectByUsername(String username) {
        UserBaseInfoPO userBaseInfoPO = userBaseInfoMapper.selectByUsername(username);
        return userBaseInfoPO;
    }

    @Override
    public UserCustomInfoVO selectUserCustomInfoByUsername(String username) {
        UserCustomInfoPO userCustomInfoPO = userCustomInfoMapper.selectByUsername(username);
        if (null == userCustomInfoPO) {
            return new UserCustomInfoVO();
        }
        UserCustomInfoVO result = new UserCustomInfoVO();
        result.setId(userCustomInfoPO.getId());
        result.setUsername(userCustomInfoPO.getUsername());
        result.setAvatarUrl(userCustomInfoPO.getAvatarUrl());
        if (userCustomInfoPO.getAdjectiveId() != null) {
            UserTitlePO userAdjTitle = userTitleMapper.selectById(userCustomInfoPO.getAdjectiveId());
            result.setAdjectiveTitle(userAdjTitle.getName());
        }
        if (userCustomInfoPO.getArticleId() != null) {
            UserTitlePO userArtTitle = userTitleMapper.selectById(userCustomInfoPO.getArticleId());
            result.setArticleTitle(userArtTitle.getName());
        }
        if (userCustomInfoPO.getSealId() != null) {
            UserSealPO userSealPO = userSealMapper.selectById(userCustomInfoPO.getSealId());
            result.setSealUrl(userSealPO.getPictureUrl());
        }
        return result;
    }

    @Override
    public List<UserAcbBorderVO> selectAcbBorder(int startIndex, int pageSize) {
        List<UserAcbBorderVO> userAcbBorderVOS = new ArrayList<>();
        List<UserBaseInfoPO> userBaseInfoPOS = userBaseInfoMapper.pagesAcbTop(startIndex, pageSize);
        for (UserBaseInfoPO userBase : userBaseInfoPOS) {
            UserAcbBorderVO userAcbBorderVO = new UserAcbBorderVO();
            userAcbBorderVO.setUsername(userBase.getUsername());
            userAcbBorderVO.setNick(userBase.getNick());
            userAcbBorderVO.setAcb(userBase.getAcb());
            userAcbBorderVOS.add(userAcbBorderVO);
        }
        return userAcbBorderVOS;
    }

    @Override
    public List<UserAcNumBorderVO> selectAcNumBorder(int startIndex, int pageSize) {
        List<UserAcNumBorderVO> userAcNumBorderVOS = new ArrayList<>();
        List<UserBaseInfoPO> userBaseInfoPOS = userBaseInfoMapper.pagesAcNumTop(startIndex, pageSize);
        for (UserBaseInfoPO userBase : userBaseInfoPOS) {
            UserAcNumBorderVO userAcbBorderVO = new UserAcNumBorderVO();
            userAcbBorderVO.setUsername(userBase.getUsername());
            userAcbBorderVO.setNick(userBase.getNick());
            userAcbBorderVO.setAcNum(userBase.getAcNum());
            userAcNumBorderVOS.add(userAcbBorderVO);
        }
        return userAcNumBorderVOS;
    }

    @Override
    public List<UserRatingBorderVO> selectRatingBorder(int startIndex, int pageSize) {
        List<UserRatingBorderVO> userRatingBorderVOS = new ArrayList<>();
        List<UserBaseInfoPO> userBaseInfoPOS = userBaseInfoMapper.pagesRatingTop(startIndex, pageSize);
        for (UserBaseInfoPO userBase : userBaseInfoPOS) {
            UserRatingBorderVO userRatingBorderVO = new UserRatingBorderVO();
            userRatingBorderVO.setUsername(userBase.getUsername());
            userRatingBorderVO.setNick(userBase.getNick());
            userRatingBorderVO.setRating(userBase.getRating());
            userRatingBorderVOS.add(userRatingBorderVO);
        }
        return userRatingBorderVOS;
    }

}

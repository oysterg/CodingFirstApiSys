package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.UserAuthMapper;
import team.fjut.cf.pojo.po.UserAuth;
import team.fjut.cf.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 *
 * @author axiang [2019/11/28]
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    UserAuthMapper userAuthMapper;

    @Override
    public Integer selectAttemptNumber(String username) {
        Example example = new Example(UserAuth.class);
        example.createCriteria().andEqualTo("username", username);
        return userAuthMapper.selectOneByExample(example).getAttemptLoginFailCount();
    }

    @Override
    public boolean isUserLocked(String username) {
        Example example = new Example(UserAuth.class);
        example.createCriteria().andEqualTo("username", username);
        UserAuth userAuth = userAuthMapper.selectOneByExample(example);
        return userAuth.getLocked() == 1;
    }

    @Override
    public Date selectUnlockTime(String username) {
        Example example = new Example(UserAuth.class);
        example.createCriteria().andEqualTo("username", username);
        return userAuthMapper.selectOneByExample(example).getUnlockTime();
    }
}

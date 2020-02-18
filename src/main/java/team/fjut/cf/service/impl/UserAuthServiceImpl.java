package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.UserAuthMapper;
import team.fjut.cf.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author axiang [2019/11/28]
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    UserAuthMapper userAuthMapper;

    @Override
    public Integer selectLockedByUsername(String username) {
        return userAuthMapper.selectIsLockedByUsername(username);
    }
}

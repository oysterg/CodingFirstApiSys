package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import team.fjut.cf.mapper.UserCheckInMapper;
import team.fjut.cf.pojo.po.UserCheckInPO;
import team.fjut.cf.service.UserCheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang [2019/10/30]
 */
@Service
public class UserCheckInServiceImpl implements UserCheckInService {
    @Autowired
    UserCheckInMapper userCheckInMapper;

    @Override
    public List<UserCheckInPO> selectByUsername(String username) {
        return userCheckInMapper.selectCheckInByUsername(username);
    }

    @Override
    public Integer selectCountTodayCheckInByUsername(String username) {
        return userCheckInMapper.selectCountTodayCheckInByUsername(username);
    }

    @Override
    public List<UserCheckInPO> pagesByUsername(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserCheckInPO> userCheckInPOS = userCheckInMapper.selectByUsername(username);
        return userCheckInPOS;
    }

    @Override
    public Integer insert(UserCheckInPO userCheckInPO) {
        return userCheckInMapper.insert(userCheckInPO);
    }
}

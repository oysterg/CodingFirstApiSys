package com.fjut.cf.service.impl;

import com.fjut.cf.mapper.UserProblemSolvedMapper;
import com.fjut.cf.pojo.po.UserProblemSolvedPO;
import com.fjut.cf.service.UserProblemSolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author axiang [2019/11/15]
 */
@Service
public class UserProblemSolvedServiceImpl implements UserProblemSolvedService {
    @Autowired
    UserProblemSolvedMapper userProblemSolvedMapper;

    @Override
    public UserProblemSolvedPO selectCountByUsernameAndProblemId(String username, Integer problemId) {
        return userProblemSolvedMapper.selectByUsernameAndProblemId(username, problemId);
    }
}

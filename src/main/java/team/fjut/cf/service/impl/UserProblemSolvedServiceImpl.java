package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.UserProblemSolvedMapper;
import team.fjut.cf.pojo.po.UserProblemSolvedPO;
import team.fjut.cf.service.UserProblemSolvedService;
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

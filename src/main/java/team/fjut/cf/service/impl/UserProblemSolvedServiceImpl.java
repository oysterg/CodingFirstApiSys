package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.UserProblemSolvedMapper;
import team.fjut.cf.pojo.po.UserProblemSolved;
import team.fjut.cf.service.UserProblemSolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author axiang [2019/11/15]
 */
@Service
public class UserProblemSolvedServiceImpl implements UserProblemSolvedService {
    @Autowired
    UserProblemSolvedMapper userProblemSolvedMapper;

    @Override
    public UserProblemSolved selectUserSolved(String username, Integer problemId) {
        Example example = new Example(UserProblemSolved.class);
        example.createCriteria().andEqualTo("username", username)
                .andEqualTo("problemId", problemId);
        return userProblemSolvedMapper.selectOneByExample(example);
    }
}

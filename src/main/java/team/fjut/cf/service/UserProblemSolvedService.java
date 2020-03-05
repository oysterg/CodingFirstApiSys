package team.fjut.cf.service;

import team.fjut.cf.pojo.po.UserProblemSolved;

/**
 * @author axiang [2019/11/15]
 */
public interface UserProblemSolvedService {
    /**
     * 根据用户名和题目ID查询解题记录
     *
     * @param username
     * @param problemId
     * @return
     */
    UserProblemSolved selectCountByUsernameAndProblemId(String username, Integer problemId);
}

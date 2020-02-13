package com.fjut.cf.service;

import com.fjut.cf.pojo.po.UserProblemSolvedPO;

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
    UserProblemSolvedPO selectCountByUsernameAndProblemId(String username, Integer problemId);
}

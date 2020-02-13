package com.fjut.cf.service;

import com.fjut.cf.pojo.po.ContestProblemPO;

import java.util.List;

/**
 * @author axiang [2019/11/21]
 */
public interface ContestProblemService {
    /**
     * 根据比赛ID查询比赛题目
     *
     * @param contestId
     * @return
     */
    List<ContestProblemPO> selectByContestId(Integer contestId);
}

package team.fjut.cf.service;

import team.fjut.cf.pojo.po.ProblemInfo;

/**
 * 题目信息Service
 *
 * @author axiang
 */
public interface ProblemInfoService {
    /**
     * 根据题目ID查询题目基本信息
     *
     * @param problemId
     * @return
     */
    ProblemInfo selectProblemInfo(Integer problemId);

    /**
     * 根据题目ID删除题目
     */
    int deleteProblem(Integer problemId);

}

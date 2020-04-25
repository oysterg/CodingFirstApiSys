package team.fjut.cf.service;

import team.fjut.cf.pojo.po.ProblemInfo;

import java.util.List;

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
     * @author zhongml [2020/4/17]
     * 根据题目ID删除题目
     *
     * @param problemId
     * @return
     */
    int deleteProblem(Integer problemId);

    /**
     * @author zhongml [2020/4/17]
     * 查询所有题目
     *
     * @return
     */
    List<ProblemInfo> selectAll();

}

package team.fjut.cf.service;

import team.fjut.cf.pojo.po.VjJudgeResult;
import team.fjut.cf.pojo.po.VjUserProblemSolved;

/**
 * @author axiang [2020/3/24]
 */
public interface VjUserProblemSolvedService {
    /**
     * 插入一条记录
     *
     * @param vjUserProblemSolved
     * @return
     */
    int insert(VjUserProblemSolved vjUserProblemSolved);

    /**
     * 如果提交成功
     *
     * @param vjJudgeResult
     */
    void ifSubmitSuccess(VjJudgeResult vjJudgeResult);

    /**
     * 根据内容查找一条记录
     *
     * @param vjUserProblemSolved
     * @return
     */
    VjUserProblemSolved selectOne(VjUserProblemSolved vjUserProblemSolved);
}

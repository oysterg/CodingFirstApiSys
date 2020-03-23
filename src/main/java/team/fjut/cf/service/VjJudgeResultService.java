package team.fjut.cf.service;

import team.fjut.cf.pojo.po.VjJudgeResult;

/**
 * @author axiang
 */
public interface VjJudgeResultService {
    /**
     * 插入一条记录
     *
     * @param vjJudgeResult
     * @return
     */
    int insert(VjJudgeResult vjJudgeResult);

    /**
     * 获取一个记录
     * @param id
     * @return
     */
    VjJudgeResult select(int id);

    /**
     * 从VJ中获取结果
     *
     * @param vjJudgeResult
     */
    void getResultFromVJ(VjJudgeResult vjJudgeResult);
}

package team.fjut.cf.service;

import team.fjut.cf.pojo.po.JudgeResultPO;

/**
 * @author axiang [2019/11/8]
 */
public interface JudgeResultService {
    /**
     * 根据评测ID 获取评测结果
     *
     * @param judgeId
     * @return
     */
    JudgeResultPO queryJudgeResultByJudgeId(Integer judgeId);
}

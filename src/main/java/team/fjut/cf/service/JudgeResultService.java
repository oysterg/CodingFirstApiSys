package team.fjut.cf.service;

import team.fjut.cf.pojo.po.JudgeResult;

import java.util.List;

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
    List<JudgeResult> selectJudgeResult(Integer judgeId);
}

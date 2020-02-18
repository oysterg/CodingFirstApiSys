package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.JudgeResultPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [2019/11/8]
 */
public interface JudgeResultMapper {
    /**
     * 插入一条评测结果记录
     *
     * @param judgeResultPO
     * @return
     */
    Integer insert(@Param("judgeResultPO") JudgeResultPO judgeResultPO);

    /**
     * 根据评测ID 获取评测结果
     *
     * @param judgeId
     * @return
     */
    JudgeResultPO selectByJudgeId(@Param("judgeId") Integer judgeId);
}

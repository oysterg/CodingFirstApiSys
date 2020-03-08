package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.JudgeResult;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author axiang [2019/11/8]
 */
public interface JudgeResultMapper extends Mapper<JudgeResult> {

    /**
     * 根据评测ID 获取评测结果
     *
     * @param judgeId
     * @return
     */
    JudgeResult selectByJudgeId(@Param("judgeId") Integer judgeId);
}

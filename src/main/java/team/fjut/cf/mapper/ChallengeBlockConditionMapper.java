package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.ChallengeBlockConditionPO;
import team.fjut.cf.pojo.vo.ChallengeBlockConditionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
public interface ChallengeBlockConditionMapper {
    /**
     * 查询模块之间的关系
     *
     * @return
     */
    List<ChallengeBlockConditionPO> all();


    /**
     * 查询模块解锁条件
     *
     * @param blockId
     * @return
     */
    List<ChallengeBlockConditionVO> selectConditionByBlockId(@Param("blockId") Integer blockId);

    /**
     * 查询后置模块
     *
     * @param blockId
     * @return
     */
    List<Integer> selectRearBlocksByBlockId(@Param("blockId") Integer blockId);
}

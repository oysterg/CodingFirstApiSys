package team.fjut.cf.mapper;

import team.fjut.cf.pojo.vo.ChallengeBlockProblemVO;
import team.fjut.cf.pojo.vo.UserChallengeBlockVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
public interface ChallengeBlockProblemMapper {

    /**
     * 分页查询模块内的题目
     *
     * @param blockId
     * @return
     */
    List<ChallengeBlockProblemVO> selectAllAsVO(@Param("blockId") Integer blockId);

    /**
     * 查询模块内题目的总数量
     *
     * @param blockId
     * @return
     */
    Integer selectAllCount(@Param("blockId") Integer blockId);

    /**
     * 获取用户模块解锁分数
     *
     * @param username
     * @return
     */
    List<UserChallengeBlockVO> selectScoredByUsername(@Param("username") String username);

    /**
     * 查询题目隶属哪个挑战模式模块
     *
     * @param problemId
     * @return
     */
    Integer selectByProblemId(@Param("problemId") Integer problemId);

    /**
     * 查询用户在某个模块下的得分情况
     *
     * @param username
     * @param blockId
     * @return
     */
    Integer selectScoredByBlockIdAndUsername(@Param("blockId") Integer blockId, @Param("username") String username);

    /**
     * 查询模块的全部积分
     *
     * @param blockId
     * @return
     */
    Integer selectTotalScoreByBlockId(@Param("blockId") Integer blockId);

}

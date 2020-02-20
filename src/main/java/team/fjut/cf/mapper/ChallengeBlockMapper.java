package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.ChallengeBlockPO;
import team.fjut.cf.pojo.vo.UserChallengeBlockVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
public interface ChallengeBlockMapper {
    /**
     * 查询用户全部挑战模式模块
     *
     * @return
     */
    List<UserChallengeBlockVO> allAsVO();

    /**
     * 根据模块ID查询模块详情
     *
     * @param blockId
     * @return
     */
    ChallengeBlockPO selectByBlockId(@Param("blockId") Integer blockId);


    /**
     * 根据用户名获取可以展示的挑战模式ID
     *
     * @param username
     * @return
     */
    List<Integer> selectCanShowedBlockIdByUsername(@Param("username") String username);

}

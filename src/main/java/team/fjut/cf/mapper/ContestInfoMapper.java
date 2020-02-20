package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.ContestInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/11/18]
 */
public interface ContestInfoMapper {
    /**
     * 根据条件分页查询不同类型比赛列表
     *
     * @param kind
     * @param searchTitle
     * @param searchPermission
     * @param searchStatus
     * @return
     */
    List<ContestInfoPO> selectAllByConditions(@Param("kind") Integer kind,
                                              @Param("searchTitle") String searchTitle,
                                              @Param("searchPermission") Integer searchPermission,
                                              @Param("searchStatus") Integer searchStatus);

    /**
     * 根据条件查询不同比赛类型记录数
     *
     * @param kind
     * @param searchTitle
     * @param searchPermission
     * @param searchStatus
     * @return
     */
    Integer selectCountByConditions(@Param("kind") Integer kind,
                                    @Param("searchTitle") String searchTitle,
                                    @Param("searchPermission") Integer searchPermission,
                                    @Param("searchStatus") Integer searchStatus);


    /**
     * 根据比赛ID查询比赛信息
     * @param contestId
     * @return
     */
    ContestInfoPO selectByContestId(@Param("contestId") Integer contestId);
}

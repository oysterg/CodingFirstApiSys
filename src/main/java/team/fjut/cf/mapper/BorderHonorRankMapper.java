package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.BorderHonorRankPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
public interface BorderHonorRankMapper {
    /**
     * 查询全部榜单
     *
     * @return
     */
    List<BorderHonorRankPO> all();

    /**
     * 查询榜单记录数
     *
     * @return
     */
    Integer allCount();

    /**
     * 根据用户名查询用户参与榜单
     *
     * @param username
     * @return
     */
    List<BorderHonorRankPO> selectByUsername(@Param("username") String username);
}

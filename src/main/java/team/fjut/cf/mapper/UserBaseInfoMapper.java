package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.UserBaseInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author axiang [2019/10/11]
 */
public interface UserBaseInfoMapper extends Mapper<UserBaseInfo> {
    /**
     * 更新用户AC题数+1
     *
     * @param username
     * @return
     */
    Integer updateAcNumAddOneByUsername(@Param("username") String username);


    /**
     * 根据用户名查找用户基本信息
     *
     * @param username
     * @return
     */
    UserBaseInfo selectByUsername(@Param("username") String username);

    /**
     * 查询ACB榜单
     *
     * @return
     */
    List<UserBaseInfo> allAcbTop();

    /**
     * 查询AC题数榜单
     *
     * @return
     */
    List<UserBaseInfo> allAcNumTop();

    /**
     * 查询积分榜单
     *
     * @return
     */
    List<UserBaseInfo> allRatingTop();
}

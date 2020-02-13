package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.UserBaseInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/10/11]
 */
public interface UserBaseInfoMapper {
    /**
     * 增加一条用户基本信息
     *
     * @param userBaseInfo
     * @return
     */
    Integer insert(@Param("userBaseInfo") UserBaseInfoPO userBaseInfo);

    /**
     * 根据用户名删除一条用户基本信息
     *
     * @param username
     * @return
     */
    Integer deleteByUsername(@Param("username") String username);

    /**
     * 根据用户名修改一条用户基本消息
     *
     * @param username
     * @param userBaseInfo
     * @return
     */
    Integer updateByUsername(@Param("username") String username, @Param("userBaseInfo") UserBaseInfoPO userBaseInfo);

    /**
     * 更新用户AC题数+1
     *
     * @param username
     * @return
     */
    Integer updateAcNumAddOneByUsername(@Param("username") String username);

    /**
     * 查找用户基本信息
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<UserBaseInfoPO> pages(@Param("startIndex") int startIndex,
                               @Param("pageSize") int pageSize);

    /**
     * 根据用户名查找用户基本信息
     *
     * @param username
     * @return
     */
    UserBaseInfoPO selectByUsername(@Param("username") String username);

    /**
     * 根据用户名查询用户基本信息数量
     *
     * @param username
     * @return
     */
    Integer selectCountByUsername(@Param("username") String username);

    /**
     * 分页查询ACB榜单
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<UserBaseInfoPO> pagesAcbTop(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 分页查询AC题数榜单
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<UserBaseInfoPO> pagesAcNumTop(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 分页查询积分榜单
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<UserBaseInfoPO> pagesRatingTop(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
}

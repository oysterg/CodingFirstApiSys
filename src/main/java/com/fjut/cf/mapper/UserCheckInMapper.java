package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.UserCheckInPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/10/23]
 */
public interface UserCheckInMapper {
    /**
     * 插入一条签到记录
     *
     * @param userCheckInPO
     * @return
     */
    Integer insert(@Param("userCheckInPO") UserCheckInPO userCheckInPO);

    /**
     * 根据用户名查询全部用户签到记录
     * @param username
     * @return
     */
    List<UserCheckInPO> selectCheckInByUsername(@Param("username") String username);


    /**
     * 根据用户名查询用户今天的签到次数
     * @param username
     * @return
     */
    Integer selectCountTodayCheckInByUsername(@Param("username") String username);

    /**
     * 根据用户分页查询签到记录
     * @param username
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<UserCheckInPO> pagesByUsername(@Param("username") String username,
                                        @Param("startIndex")Integer startIndex,
                                        @Param("pageSize") Integer pageSize);

}

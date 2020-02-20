package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.UserCheckInPO;
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
     * 根据用户查询签到记录
     * @param username
     * @return
     */
    List<UserCheckInPO> selectByUsername(@Param("username") String username);

}

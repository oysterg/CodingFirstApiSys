package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.UserAuthPO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author axiang [2019/10/11]
 */
public interface UserAuthMapper {
    /**
     * 插入一条用户登录权限记录
     *
     * @param tableUserAuth
     * @return
     */
    Integer insert(@Param("UserAuth") UserAuthPO tableUserAuth);

    /**
     * 根据用户名删除一条用户登录权限记录
     *
     * @param username
     * @return
     */
    Integer deleteByUsername(@Param("username") String username);

    /**
     * 用户登录的尝试次数 + times
     *
     * @param username
     * @param times
     * @return
     */
    Integer updateAttemptFailByUsername(@Param("username") String username,
                                        @Param("times") Integer times);

    /**
     * 用户登录的尝试次数清空
     *
     * @param username
     * @return
     */
    Integer updateAttemptFailSetZeroByUsername(@Param("username") String username);

    /**
     * 更新用户登录的解锁时间
     *
     * @param unlockTime
     * @param username
     * @return
     */
    Integer updateUnlockTimeByUsername(@Param("unlockTime") Date unlockTime,
                                       @Param("username") String username);

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return
     */
    Integer selectCountByUsername(String username);

    /**
     * 根据用户名密码查询满足的记录数
     *
     * @param username
     * @param password
     * @return
     */
    Integer selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询用户密码盐值
     *
     * @param username
     * @return
     */
    String selectSaltByUsername(@Param("username") String username);

    /**
     * 查询用户的登录失败次数
     *
     * @param username
     * @return
     */
    Integer selectAttemptNumberByUsername(@Param("username") String username);

    /**
     * 查询用户登录的解锁时间
     *
     * @param username
     * @return
     */
    Date selectUnlockTimeByUsername(@Param("username") String username);

    /**
     * 查询用户是否锁定
     *
     * @param username
     * @return
     */
    Integer selectIsLockedByUsername(@Param("username") String username);
}

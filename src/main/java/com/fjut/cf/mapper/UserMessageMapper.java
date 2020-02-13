package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.UserMessagePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
public interface UserMessageMapper {

    /**
     * 插入一条邮件信息
     *
     * @param userMessagePO
     * @return
     */
    Integer insert(@Param("userMessagePO") UserMessagePO userMessagePO);

    /**
     * 设置用户特定的消息已读
     *
     * @param username
     * @param id
     * @return
     */
    Integer updateSetReadByUsernameAndId(@Param("username") String username, @Param("id") Integer id);

    /**
     * 设置用户特定的消息已读
     *
     * @param username
     * @return
     */
    Integer updateSetAllReadByUsername(@Param("username") String username);

    /**
     * 根据用户名分页查询用户消息记录
     *
     * @param username
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<UserMessagePO> pagesByUsername(@Param("username") String username, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据用户名查询消息记录数
     *
     * @param username
     * @return
     */
    Integer selectCountUserMessageByUsername(@Param("username") String username);

    /**
     * 根据用户名分页查询用户消息记录
     *
     * @param username
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<UserMessagePO> pagesUnreadByUsername(@Param("username") String username, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据用户名查询消息记录数
     *
     * @param username
     * @return
     */
    Integer selectCountUnreadByUsername(@Param("username") String username);
}

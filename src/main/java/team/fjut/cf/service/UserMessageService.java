package team.fjut.cf.service;

import team.fjut.cf.pojo.po.UserMessagePO;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
public interface UserMessageService {
    /**
     * 插入一条邮件信息
     *
     * @param userMessagePO
     * @return
     */
    Integer insert(UserMessagePO userMessagePO);


    /**
     * 设置用户的特定消息已读
     *
     * @param username
     * @param id
     * @return
     */
    Integer updateSetReadByUsernameAndId(String username, Integer id);

    /**
     * 设置用户的特定消息已读
     *
     * @param username
     * @return
     */
    Integer updateSetReadByUsername(String username);

    /**
     * 根据用户名分页查询用户消息记录
     *
     * @param username
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<UserMessagePO> pagesByUsername(String username, Integer startIndex, Integer pageSize);

    /**
     * 根据用户名查询用户消息
     *
     * @param username
     * @return
     */
    Integer selectCountUserMessageByUsername(String username);

    /**
     * 根据用户名分页查询用户未读消息记录
     *
     * @param username
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<UserMessagePO> pagesUnreadByUsername(String username, Integer startIndex, Integer pageSize);

    /**
     * 根据用户名查询用户消息
     *
     * @param username
     * @return
     */
    Integer selectCountUnreadByUsername(String username);
}

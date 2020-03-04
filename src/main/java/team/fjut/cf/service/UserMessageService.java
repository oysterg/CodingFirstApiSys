package team.fjut.cf.service;

import team.fjut.cf.pojo.po.UserMessage;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
public interface UserMessageService {
    /**
     * 插入一条邮件信息
     *
     * @param userMessage
     * @return
     */
    Integer insert(UserMessage userMessage);


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
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserMessage> pagesByUsername(String username, Integer pageNum, Integer pageSize);

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
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserMessage> pagesUnreadByUsername(String username, Integer pageNum, Integer pageSize);

    /**
     * 根据用户名查询用户消息
     *
     * @param username
     * @return
     */
    Integer selectCountUnreadByUsername(String username);
}

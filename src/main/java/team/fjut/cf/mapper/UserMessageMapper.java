package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.UserMessage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
public interface UserMessageMapper extends Mapper<UserMessage> {

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
     * 根据用户名查询用户消息记录
     *
     * @param username
     * @return
     */
    List<UserMessage> selectByUsername(@Param("username") String username);

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
     * @return
     */
    List<UserMessage> selectUnreadByUsername(@Param("username") String username);

    /**
     * 根据用户名查询消息记录数
     *
     * @param username
     * @return
     */
    Integer selectCountUnreadByUsername(@Param("username") String username);
}

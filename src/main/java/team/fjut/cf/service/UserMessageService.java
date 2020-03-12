package team.fjut.cf.service;

import team.fjut.cf.pojo.vo.UserMessageListVO;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
public interface UserMessageService {


    /**
     * 根据条件分页查询用户消息记录
     *
     * @param pageNum
     * @param pageSize
     * @param toUsername
     * @param fromUsername
     * @param status
     * @param title
     * @return
     */
    List<UserMessageListVO> pagesByConditions(int pageNum, int pageSize,
                                              String toUsername,
                                              String fromUsername, Integer status, String title);


    /**
     * 根据条件分页查询用户消息记录数量
     *
     * @param toUsername
     * @param fromUsername
     * @param status
     * @param title
     * @return
     */
    Integer countByConditions(String toUsername,
                              String fromUsername, Integer status, String title);

}

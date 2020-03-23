package team.fjut.cf.service;

import team.fjut.cf.pojo.vo.UserCustomInfoVO;

/**
 * @author axiang
 */
public interface UserCustomInfoService {
    /**
     * 根据用户名查找一条记录
     *
     * @param username
     * @return
     */
    UserCustomInfoVO select(String username);
}

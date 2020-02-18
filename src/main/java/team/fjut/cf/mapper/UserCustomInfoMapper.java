package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.UserCustomInfoPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [2019/10/14]
 */
public interface UserCustomInfoMapper {

    /**
     * 插入一条用户个性化信息
     *
     * @param userCustomInfoPO
     * @return
     */
    Integer insert(@Param("userCustomInfoPO") UserCustomInfoPO userCustomInfoPO);

    /**
     * 根据用户名查询用户个性化信息
     *
     * @param username
     * @return
     */
    UserCustomInfoPO selectByUsername(@Param("username") String username);
}

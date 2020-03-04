package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.UserCustomInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author axiang [2019/10/14]
 */
public interface UserCustomInfoMapper extends Mapper<UserCustomInfo> {
    /**
     * 根据用户名查询用户个性化信息
     *
     * @param username
     * @return
     */
    UserCustomInfo selectByUsername(@Param("username") String username);
}

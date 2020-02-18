package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.UserSealRecordPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [2019/11/12]
 */
public interface UserSealRecordMapper {
    /**
     * 根据用户名查询用户获得印章记录
     *
     * @param username
     * @return
     */
    UserSealRecordPO selectByUsername(@Param("username") String username);
}

package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.UserPermissionPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/10/18]
 */
public interface UserPermissionMapper {
    /**
     * 插入一条用户权限详情
     *
     * @param userPermissionPO
     * @return
     */
    Integer insert(@Param("userPermissionPO") UserPermissionPO userPermissionPO);


    /**
     * 获取某个用户的全部权限详情
     *
     * @param username
     * @return
     */
    List<UserPermissionPO> selectByUsername(@Param("username") String username);

    /**
     * 获取用户的某项权限是否存在
     *
     * @param username
     * @param id
     * @return
     */
    Integer selectCountByUsername(@Param("username") String username, @Param("id") Integer id);

}

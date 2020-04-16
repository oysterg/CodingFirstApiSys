package team.fjut.cf.service;

import team.fjut.cf.pojo.enums.PermissionType;

/**
 * @author axiang [2020/4/16]
 */
public interface UserPermissionService {
    /**
     * 检查用户是否拥有全部权限
     *
     * @param username
     * @param permissions
     * @return
     */
    boolean isUserHaveAllPermissions(String username, PermissionType[] permissions);
}

package team.fjut.cf.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.fjut.cf.mapper.UserPermissionMapper;
import team.fjut.cf.pojo.enums.PermissionType;
import team.fjut.cf.pojo.po.UserPermission;
import team.fjut.cf.service.UserPermissionService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author axiang [2020/4/16]
 */
@Service
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
public class UserPermissionServiceImpl implements UserPermissionService {
    @Resource
    UserPermissionMapper userPermissionMapper;

    @Override
    public boolean isUserHaveAllPermissions(String username, PermissionType[] needPermissions) {
        Example example = new Example(UserPermission.class);
        example.createCriteria().andEqualTo("username", username);
        List<UserPermission> havePermissions = userPermissionMapper.selectByExample(example);
        ArrayList<PermissionType> list = new ArrayList<>();
        for (UserPermission havePermission : havePermissions) {
            list.add(PermissionType.getEnumByID(havePermission.getPermissionId()));
        }
        // 如果没有基础权限，则认定非管理员
        if(!list.contains(PermissionType.BASE_ADMIN))
        {
            return false;
        }
        for (PermissionType needPermission : needPermissions) {
            if (!list.contains(needPermission)) {
                return false;
            }
        }
        return true;
    }
}

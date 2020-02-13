package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.UserTitlePO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [2019/11/12]
 */
public interface UserTitleMapper {
    /**
     * 根据ID 查询用户头衔记录
     * @param id
     * @return
     */
    UserTitlePO selectById(@Param("id") Integer id);
}

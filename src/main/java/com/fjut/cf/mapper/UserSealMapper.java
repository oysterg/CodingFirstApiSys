package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.UserSealPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [2019/11/12]
 */
public interface UserSealMapper {

    /**
     * 根据ID 查询印章信息记录
     *
     * @param id
     * @return
     */
    UserSealPO selectById(@Param("id") Integer id);

}

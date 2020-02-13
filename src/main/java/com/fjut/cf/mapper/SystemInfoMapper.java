package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.SystemInfoPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [2019/10/30]
 */
public interface SystemInfoMapper {

    /**
     * 根据名字查询系统字段
     *
     * @param name
     * @return
     */
    SystemInfoPO queryByName(@Param("name") String name);
}

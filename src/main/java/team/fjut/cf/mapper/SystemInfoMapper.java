package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.SystemInfoPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [2019/10/30]
 */
public interface SystemInfoMapper {
    /**
     * 插入一条记录
     *
     * @param systemInfoPO
     * @return
     */
    Integer insert(@Param("systemInfoPO") SystemInfoPO systemInfoPO);

    /**
     * 根据ID更新一条记录
     *
     * @param systemInfoPO
     * @return
     */
    Integer update(@Param("systemInfoPO") SystemInfoPO systemInfoPO);

    /**
     * 根据名字查询系统字段
     *
     * @param name
     * @return
     */
    SystemInfoPO selectByName(@Param("name") String name);
}

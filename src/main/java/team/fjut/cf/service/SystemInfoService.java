package team.fjut.cf.service;

import team.fjut.cf.pojo.po.SystemInfoPO;

/**
 * @author axiang [2019/10/30]
 */
public interface SystemInfoService {
    /**
     * 插入一条记录
     *
     * @param systemInfoPO
     * @return
     */
    Integer insert(SystemInfoPO systemInfoPO);

    /**
     * 更新记录
     *
     * @param systemInfoPO
     * @return
     */
    Integer update(SystemInfoPO systemInfoPO);

    /**
     * 根据名字查询系统字段
     *
     * @param name
     * @return
     */
    SystemInfoPO selectByName(String name);

}

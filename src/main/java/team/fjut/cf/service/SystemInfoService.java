package team.fjut.cf.service;

import team.fjut.cf.pojo.po.SystemInfo;

/**
 * @author axiang [2019/10/30]
 */
public interface SystemInfoService {
    /**
     * 插入一条记录
     *
     * @param systemInfo
     * @return
     */
    int insert(SystemInfo systemInfo);

    /**
     * 更新记录
     *
     * @param systemInfo
     * @return
     */
    Integer update(SystemInfo systemInfo);

    /**
     * 根据名字查询系统字段
     *
     * @param name
     * @return
     */
    SystemInfo selectOne(String name);

}

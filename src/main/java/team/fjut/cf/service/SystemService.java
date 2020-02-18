package team.fjut.cf.service;

import team.fjut.cf.pojo.po.SystemInfoPO;

/**
 * @author axiang [2019/10/30]
 */
public interface SystemService {
    /**
     * 根据名字查询系统字段
     *
     * @param name
     * @return
     */
    SystemInfoPO queryByName(String name);

}

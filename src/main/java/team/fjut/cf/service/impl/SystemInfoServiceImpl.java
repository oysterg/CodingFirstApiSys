package team.fjut.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.fjut.cf.mapper.SystemInfoMapper;
import team.fjut.cf.pojo.po.SystemInfoPO;
import team.fjut.cf.service.SystemInfoService;

/**
 * @author axiang [2019/10/30]
 */
@Service
public class SystemInfoServiceImpl implements SystemInfoService {
    @Autowired
    SystemInfoMapper systemInfoMapper;


    @Override
    public Integer insert(SystemInfoPO systemInfoPO) {
        return systemInfoMapper.insert(systemInfoPO);
    }

    @Override
    public Integer update(SystemInfoPO systemInfoPO) {
        return systemInfoMapper.update(systemInfoPO);
    }

    @Override
    public SystemInfoPO selectByName(String name) {
        return systemInfoMapper.selectByName(name);
    }
}

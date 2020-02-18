package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.SystemInfoMapper;
import team.fjut.cf.pojo.po.SystemInfoPO;
import team.fjut.cf.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author axiang [2019/10/30]
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    SystemInfoMapper systemInfoMapper;

    @Override
    public SystemInfoPO queryByName(String name) {
        return systemInfoMapper.queryByName(name);
    }
}

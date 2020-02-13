package com.fjut.cf.service.impl;

import com.fjut.cf.mapper.BugReportedMapper;
import com.fjut.cf.pojo.po.BugReportPO;
import com.fjut.cf.service.BugReportedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author axiang [2019/11/15]
 */
@Service
public class BugReportedServiceImpl implements BugReportedService {
    @Autowired
    BugReportedMapper bugReportedMapper;

    @Override
    public Integer insert(BugReportPO bugReportPO) {
        return bugReportedMapper.insert(bugReportPO);
    }
}

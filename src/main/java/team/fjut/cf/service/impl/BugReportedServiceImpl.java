package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.BugReportedMapper;
import team.fjut.cf.pojo.po.BugReport;
import team.fjut.cf.service.BugReportedService;
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
    public int insert(BugReport bugReport) {
        return bugReportedMapper.insert(bugReport);
    }
}

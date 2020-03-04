package team.fjut.cf.service;

import team.fjut.cf.pojo.po.BugReport;

/**
 * @author axiang [2019/11/15]
 */
public interface BugReportedService {
    /**
     * 插入bug反馈记录
     *
     * @param bugReport
     * @return
     */
    int insert(BugReport bugReport);
}

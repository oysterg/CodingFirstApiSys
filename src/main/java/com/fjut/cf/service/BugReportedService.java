package com.fjut.cf.service;

import com.fjut.cf.pojo.po.BugReportPO;

/**
 * @author axiang [2019/11/15]
 */
public interface BugReportedService {
    /**
     * 插入bug反馈记录
     * @param bugReportPO
     * @return
     */
    Integer insert(BugReportPO bugReportPO);
}

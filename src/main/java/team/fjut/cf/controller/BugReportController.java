package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.BugReport;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.BugReportedService;

import java.util.Date;

/**
 * @author axiang [2019/11/15]
 */
@RestController
@CrossOrigin
@RequestMapping("/bug")
public class BugReportController {
    @Autowired
    BugReportedService bugReportedService;

    /**
     * 报告bug
     *
     * @param username 用户名
     * @param currentPath 当前路径
     * @param type 0
     * @param title BUG标题
     * @param text BUG内容
     * @return resultJson
     */
    @PostMapping("/report")
    public ResultJson reportBug(@RequestParam("username") String username,
                                @RequestParam("currentPath") String currentPath,
                                @RequestParam(value = "type", defaultValue = "0") Integer type,
                                @RequestParam("title") String title,
                                @RequestParam("text") String text) {
        ResultJson resultJson = new ResultJson();
        BugReport bugReport = new BugReport();
        bugReport.setUsername(username);
        bugReport.setCurrentPath(currentPath);
        bugReport.setType(type);
        bugReport.setTitle(title);
        bugReport.setText(text);
        bugReport.setIsFixed(0);
        bugReport.setReportTime(new Date());
        int ans = bugReportedService.insert(bugReport);
        if (1 == ans) {
            resultJson.setStatus(ResultCode.REQUIRED_SUCCESS);
        } else {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }
}

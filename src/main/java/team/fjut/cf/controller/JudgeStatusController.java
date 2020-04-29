package team.fjut.cf.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.judge.local.LocalJudgeHttpClient;
import team.fjut.cf.component.judge.local.pojo.LocalJudgeSubmitInfoParams;
import team.fjut.cf.config.interceptor.annotation.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.enums.SubmitResult;
import team.fjut.cf.pojo.po.JudgeStatus;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.pojo.vo.StatusCountVO;
import team.fjut.cf.pojo.vo.response.StatusListVO;
import team.fjut.cf.service.JudgeStatusService;
import team.fjut.cf.service.ViewJudgeStatusService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author axiang [2019/10/30]
 */
@RestController
@CrossOrigin
@RequestMapping("/judge_status")
public class JudgeStatusController {
    @Resource
    JudgeStatusService judgeStatusService;

    @Resource
    ViewJudgeStatusService viewJudgeStatusService;

    @Resource
    LocalJudgeHttpClient localJudgeHttpClient;

    @GetMapping("/list")
    public ResultJson getStatusList(@RequestParam("pageNum") Integer pageNum,
                                    @RequestParam("pageSize") Integer pageSize,
                                    @RequestParam(value = "contestId", required = false) Integer contestId,
                                    @RequestParam(value = "nickname", required = false) String nickname,
                                    @RequestParam(value = "problemId", required = false) Integer problemId,
                                    @RequestParam(value = "result", required = false) Integer result,
                                    @RequestParam(value = "language", required = false) Integer language) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize || pageSize > 100) {
            pageSize = 50;
        }
        if (!StringUtils.isEmpty(nickname)) {
            nickname = "%" + nickname + "%";
        } else {
            nickname = null;
        }
        List<StatusListVO> statusListVOS = viewJudgeStatusService.pagesByConditions(pageNum, pageSize, contestId, nickname, problemId, result, language);
        int count = viewJudgeStatusService.countByConditions(contestId, nickname, problemId, result, language);
        resultJson.addInfo(statusListVOS);
        resultJson.addInfo(count);
        return resultJson;
    }

    @GetMapping("/count")
    public ResultJson getStatusCountByDay(@RequestParam(value = "days", required = false) String daysStr) {
        int days;
        days = daysStr == null ? 60 : Integer.parseInt(daysStr);
        ResultJson resultJson = new ResultJson();
        HashMap<Date, StatusCountVO> hashMap = new HashMap<>(105);
        for (int i = 0; i < days; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -days + 1 + i);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            StatusCountVO statusCountVO = new StatusCountVO();
            statusCountVO.setTotalCount(0);
            statusCountVO.setAcCount(0);
            statusCountVO.setSubmitDay(calendar.getTime());
            hashMap.put(calendar.getTime(), statusCountVO);
        }
        List<StatusCountVO> statusCountVOS = judgeStatusService.selectCountByDay(days);
        for (StatusCountVO statusCountVO : statusCountVOS) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(statusCountVO.getSubmitDay());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            hashMap.put(calendar.getTime(), statusCountVO);
        }
        List<StatusCountVO> ans = new ArrayList<>();
        for (Date date : hashMap.keySet()) {
            StatusCountVO statusCountVO = hashMap.get(date);
            ans.add(statusCountVO);
        }
        //按时间升序
        Collections.sort(ans, Comparator.comparing(StatusCountVO::getSubmitDay));
        resultJson.setStatus(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(ans);
        return resultJson;
    }

    /**
     * 本地判题
     * 业务逻辑如下：
     * 1. 将评测记录插入数据库中
     * 2. 提交到评测机评测
     * 3. 把取结果的业务放到线程池中去做
     *
     * @param problemId
     * @param code
     * @param languageStr
     * @param username
     * @param contestId
     * @return
     * @throws Exception
     */
    @PrivateRequired
    @PostMapping("/submit")
    public ResultJson submitToLocalJudge(@RequestParam("problemId") Integer problemId,
                                         @RequestParam("code") String code,
                                         @RequestParam("language") String languageStr,
                                         @RequestParam("username") String username,
                                         @RequestParam(value = "contestId", required = false) Integer contestId) throws Exception {
        ResultJson resultJson = new ResultJson();
        Date currentTime = new Date();
        // 目前支持语言 JAVA Python2 C/C++
        int language = ("JAVA").equalsIgnoreCase(languageStr) ?
                2 : ("Python2").equalsIgnoreCase(languageStr) ? 3 : 1;
        JudgeStatus judgeStatus = new JudgeStatus();
        judgeStatus.setUsername(username);
        judgeStatus.setProblemId(problemId);
        judgeStatus.setContestId(contestId);
        judgeStatus.setLanguage(language);
        judgeStatus.setSubmitTime(currentTime);
        judgeStatus.setResult(SubmitResult.PENDING.getCode());
        judgeStatus.setTimeUsed("-");
        judgeStatus.setMemoryUsed("-");
        judgeStatus.setCode(code);
        judgeStatus.setCodeLength(code.replaceAll("\\s*", "").length());
        // 插入数据库中
        judgeStatusService.insert(judgeStatus);

        // 获得插入后的rid
        Integer runId = judgeStatus.getId();
        LocalJudgeSubmitInfoParams localJudgeSubmitInfoParams = new LocalJudgeSubmitInfoParams();
        localJudgeSubmitInfoParams.setType("submit");
        localJudgeSubmitInfoParams.setPid(problemId);
        localJudgeSubmitInfoParams.setRid(runId);
        localJudgeSubmitInfoParams.setMemoryLimit(65536);
        localJudgeSubmitInfoParams.setTimeLimit(2000);
        localJudgeSubmitInfoParams.setCode(code);
        localJudgeSubmitInfoParams.setLanguageId(language);
        JSONObject jsonObject;
        try {
            jsonObject = localJudgeHttpClient.submitToLocalJudge(localJudgeSubmitInfoParams);
        } catch (Exception e) {
            // 请求评测机出现异常，返回失败状态
            judgeStatusService.ifLocalJudgeError(judgeStatus);
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "提交代码到本地评测机失败！评测机连接异常");
            return resultJson;
        }
        // 如果提交到本地评测机成功，则
        if ("success".equals(jsonObject.getString("ret"))) {
            // 更新数据库表
            judgeStatusService.ifSubmitSuccess(judgeStatus);
            // 启用异步Service获取结果
            judgeStatusService.queryResultFromLocalJudge(judgeStatus);
            resultJson.setStatus(ResultCode.REQUIRED_SUCCESS, "提交评测成功！");
        }
        // 如果请求评测机成功，但评测机返回失败结论
        else {
            // 更新数据库表，返回结果
            judgeStatusService.ifSubmitError(judgeStatus);
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "提交代码到本地评测机失败！评测机内部异常");
        }
        return resultJson;
    }
}

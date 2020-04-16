package team.fjut.cf.controller;

import org.springframework.web.bind.annotation.*;
import team.fjut.cf.config.interceptor.annotation.LoginRequired;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.JudgeResult;
import team.fjut.cf.pojo.vo.JudgeStatusVO;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.JudgeResultService;
import team.fjut.cf.service.JudgeStatusService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author axiang [2019/11/8]
 */
@RestController
@CrossOrigin
@RequestMapping("/judge_result")
public class JudgeResultController {
    @Resource
    JudgeStatusService judgeStatusService;

    @Resource
    JudgeResultService judgeResultService;

    @LoginRequired
    @PostMapping("/info")
    public ResultJson getJudgeResult(@RequestParam("judgeId") Integer judgeId) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        List<JudgeResult> judgeResults = judgeResultService.selectJudgeResult(judgeId);
        JudgeStatusVO judgeStatusVO = judgeStatusService.selectJudgeStatus(judgeId);
        resultJson.addInfo(judgeResults);
        resultJson.addInfo(judgeStatusVO);
        return resultJson;
    }
}

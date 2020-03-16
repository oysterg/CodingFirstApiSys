package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.interceptor.LoginRequired;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.JudgeResult;
import team.fjut.cf.pojo.vo.JudgeStatusVO;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.JudgeResultService;
import team.fjut.cf.service.JudgeStatusService;

import java.util.List;

/**
 * @author axiang [2019/11/8]
 */
@RestController
@CrossOrigin
@RequestMapping("/judgeResult")
public class JudgeResultController {
    @Autowired
    JudgeStatusService judgeStatusService;

    @Autowired
    JudgeResultService judgeResultService;

    @LoginRequired
    @PostMapping("/info")
    public ResultJson getJudgeResult(@RequestParam("judgeId")Integer judgeId)
    {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        List<JudgeResult> judgeResults = judgeResultService.selectJudgeResult(judgeId);
        JudgeStatusVO judgeStatusVO = judgeStatusService.selectJudgeStatus(judgeId);
        resultJson.addInfo(judgeResults);
        resultJson.addInfo(judgeStatusVO);
        return resultJson;
    }
}

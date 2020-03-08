package team.fjut.cf.controller;

import team.fjut.cf.component.interceptor.LoginRequired;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.JudgeResult;
import team.fjut.cf.pojo.vo.JudgeStatusVO;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.service.JudgeResultService;
import team.fjut.cf.service.JudgeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author axiang [2019/11/8]
 */
@RestController
@CrossOrigin
@RequestMapping("/judge_result")
public class JudgeResultController {
    @Autowired
    JudgeStatusService judgeStatusService;

    @Autowired
    JudgeResultService judgeResultService;

    @LoginRequired
    @GetMapping("/info")
    public ResultJsonVO getJudgeResult(@RequestParam("judgeId")Integer judgeId)
    {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        JudgeResult judgeResult = judgeResultService.queryJudgeResultByJudgeId(judgeId);
        JudgeStatusVO judgeStatusVO = judgeStatusService.selectAsViewJudgeStatusById(judgeId);
        resultJsonVO.addInfo(judgeResult);
        resultJsonVO.addInfo(judgeStatusVO);
        return resultJsonVO;
    }
}

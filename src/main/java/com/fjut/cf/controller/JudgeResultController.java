package com.fjut.cf.controller;

import com.fjut.cf.component.interceptor.LoginRequired;
import com.fjut.cf.pojo.enums.ResultJsonCode;
import com.fjut.cf.pojo.po.JudgeResultPO;
import com.fjut.cf.pojo.vo.JudgeStatusVO;
import com.fjut.cf.pojo.vo.ResultJsonVO;
import com.fjut.cf.service.JudgeResultService;
import com.fjut.cf.service.JudgeStatusService;
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
        JudgeResultPO judgeResultPO = judgeResultService.queryJudgeResultByJudgeId(judgeId);
        JudgeStatusVO judgeStatusVO = judgeStatusService.selectAsViewJudgeStatusById(judgeId);
        resultJsonVO.addInfo(judgeResultPO);
        resultJsonVO.addInfo(judgeStatusVO);
        return resultJsonVO;
    }
}

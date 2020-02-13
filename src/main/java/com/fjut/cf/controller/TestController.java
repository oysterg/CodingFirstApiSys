package com.fjut.cf.controller;

import com.fjut.cf.component.email.EmailTool;
import com.fjut.cf.component.judge.local.LocalJudgeHttpClient;
import com.fjut.cf.component.redis.RedisUtils;
import com.fjut.cf.component.token.TokenManager;
import com.fjut.cf.pojo.vo.ResultJsonVO;
import com.fjut.cf.service.ChallengeBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author axiang [2019/10/21]
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    EmailTool emailTool;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    TokenManager tokenManager;

    @Autowired
    LocalJudgeHttpClient localJudgeHttpClient;

    @Autowired
    ChallengeBlockService challengeBlockService;

    @GetMapping("/test")
    public ResultJsonVO testMethod() {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        //LocalJudgeSubmitInfoBO localJudgeSubmitInfo = new LocalJudgeSubmitInfoBO();
        //localJudgeSubmitInfo.setPid(1000);
        //localJudgeSubmitInfo.setRid(12345);
        //localJudgeSubmitInfo.setCode("#include<stdio.h>");
        //localJudgeSubmitInfo.setLanguageId(0);
        //localJudgeSubmitInfo.setMemoryLimit(1000);
        //localJudgeSubmitInfo.setTimeLimit(1000);
        //localJudgeSubmitInfo.setType("submit");
        ////String s = localJudgeHttpClient.submitToLocalJudge(localJudgeSubmitInfo);
        ////resultJsonVO.addInfo(s);
        return resultJsonVO;
    }

    @GetMapping("/test1")
    public ResultJsonVO testMethod1() throws Exception {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        //emailTool.sendSimpleMailTest();
        return resultJsonVO;
    }
}

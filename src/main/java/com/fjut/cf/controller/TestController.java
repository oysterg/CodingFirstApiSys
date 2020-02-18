package com.fjut.cf.controller;

import com.fjut.cf.component.email.EmailTool;
import com.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import com.fjut.cf.component.judge.vjudge.pojo.RequestProblemHtmlParams;
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
    VirtualJudgeHttpClient virtualJudgeHttpClient;

    @Autowired
    ChallengeBlockService challengeBlockService;

    @GetMapping("/test")
    public ResultJsonVO testMethod() {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        RequestProblemHtmlParams params = new RequestProblemHtmlParams();
        params.setOJId("UVA");
        params.setProbNum("11540");
        resultJsonVO.addInfo(virtualJudgeHttpClient.getProblemHtml( params));
        return resultJsonVO;
    }
}

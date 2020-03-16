package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.vo.ResultJsonVO;

/**
 * @author axiang [2019/10/21]
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @Autowired
    VirtualJudgeHttpClient virtualJudgeHttpClient;

    @GetMapping("/test")
    public ResultJsonVO testMethod() {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        virtualJudgeHttpClient.userLogin("axiangcoding", "wuyuxiang");
        Object o = virtualJudgeHttpClient.checkIsLogin();
        System.out.println(o.toString());
        return resultJsonVO;
    }
}

package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.util.JsonFileUtils;

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
    public ResultJson testMethod() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        JsonFileUtils.getRandomVJAccount();
        return resultJson;
    }
}

package team.fjut.cf.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import team.fjut.cf.component.judge.vjudge.pojo.VjAccount;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.utils.JsonFileTool;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author axiang [2019/10/21]
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @Resource
    VirtualJudgeHttpClient virtualJudgeHttpClient;

    @Resource
    JsonFileTool jsonFileTool;

    @GetMapping(value = "/test")
    public ResultJson testMethod() throws IOException {
        VjAccount randomVJAccount = jsonFileTool.getRandomVJAccount();
        ResultJson resultJson = new ResultJson();
        resultJson.addInfo(randomVJAccount);
        return resultJson;

    }
}

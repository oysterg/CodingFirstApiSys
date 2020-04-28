package team.fjut.cf.controller.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.component.spider.SpiderHttpClient;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.vo.ResultJson;

import javax.annotation.Resource;

/**
 * @author axiang [2020/4/28]
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/spider")
public class SpiderManagerController {
    @Resource
    SpiderHttpClient spiderHttpClient;

    @PostMapping("/status")
    public ResultJson getStatus() {
        JSONObject daemonStatus = spiderHttpClient.getDaemonStatus();
        return new ResultJson(ResultCode.REQUIRED_SUCCESS, "", daemonStatus);
    }
}

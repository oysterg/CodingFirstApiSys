package team.fjut.cf.controller.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.spider.SpiderHttpClient;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.SpiderItemInfo;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.SpiderItemInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author axiang [2020/4/28]
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/spider")
public class SpiderManagerController {
    @Resource
    SpiderHttpClient spiderHttpClient;

    @Resource
    SpiderItemInfoService spiderItemInfoService;

    @PostMapping("/status")
    public ResultJson getStatus() {
        JSONObject daemonStatus = spiderHttpClient.getDaemonStatus();
        return new ResultJson(ResultCode.REQUIRED_SUCCESS, "", daemonStatus);
    }

    @PostMapping("/items")
    public ResultJson getItems(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize) {
        List<SpiderItemInfo> pages = spiderItemInfoService.pages(pageNum, pageSize);
        return new ResultJson(ResultCode.REQUIRED_SUCCESS, "", pages);
    }
}

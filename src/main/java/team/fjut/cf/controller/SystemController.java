package team.fjut.cf.controller;

import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.SystemInfo;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author axiang [2019/10/30]
 */
@RestController
@CrossOrigin
@RequestMapping("/system")
public class SystemController {
    @Autowired
    SystemInfoService systemInfoService;

    @GetMapping("/info")
    public ResultJson getSystemInfo(@RequestParam("name") String name) {
        ResultJson resultJson = new ResultJson();
        SystemInfo systemInfo = systemInfoService.selectOne(name);
        if (Objects.isNull(systemInfo)) {
            resultJson.setStatus(ResultCode.RESOURCE_NOT_EXIST, "未找到相应内容");
            return resultJson;
        }
        resultJson.addInfo(systemInfo);
        resultJson.setStatus(ResultCode.REQUIRED_SUCCESS);
        return resultJson;

    }
}

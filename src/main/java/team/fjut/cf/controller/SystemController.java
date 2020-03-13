package team.fjut.cf.controller;

import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.SystemInfo;
import team.fjut.cf.pojo.vo.ResultJsonVO;
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
    public ResultJsonVO getSystemInfo(@RequestParam("name") String name) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        SystemInfo systemInfo = systemInfoService.selectOne(name);
        if (Objects.isNull(systemInfo)) {
            resultJsonVO.setStatus(ResultJsonCode.RESOURCE_NOT_EXIST, "未找到相应内容");
            return resultJsonVO;
        }
        resultJsonVO.addInfo(systemInfo);
        resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
        return resultJsonVO;

    }
}

package team.fjut.cf.controller;

import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.SystemInfoPO;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.service.SystemService;
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
    SystemService systemService;

    @GetMapping("/info")
    public ResultJsonVO getSystemInfoByName(@RequestParam("name") String name) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        SystemInfoPO systemInfoPO = systemService.queryByName(name);
        if (Objects.isNull(systemInfoPO)) {
            resultJsonVO.setStatus(ResultJsonCode.RESOURCE_NOT_EXIST, "未找到相应内容");
            return resultJsonVO;
        }
        resultJsonVO.addInfo(systemInfoPO);
        resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
        return resultJsonVO;

    }
}

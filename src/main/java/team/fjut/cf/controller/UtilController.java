package team.fjut.cf.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.util.Enums2ListUtils;

import java.util.Date;

/**
 * @author axiang [2020/2/13]
 */
@RestController
@CrossOrigin
@RequestMapping("/util")
public class UtilController {

    @GetMapping("/serverTime")
    public ResultJson getServerTime() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(new Date());
        return resultJson;
    }

    @GetMapping("/bugTypes")
    public ResultJson getBugTypes() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(Enums2ListUtils.parseBugType());
        return resultJson;
    }

    @GetMapping("/codeLanguage")
    public ResultJson getCodeLanguage() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(Enums2ListUtils.parseCodeLanguage());
        return resultJson;
    }

    @GetMapping("/submitResult")
    public ResultJson getSubmitResult() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(Enums2ListUtils.parseSubmitResult());
        return resultJson;
    }
}

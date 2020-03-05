package team.fjut.cf.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.vo.ResultJsonVO;
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
    public ResultJsonVO getServerTime() {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        resultJsonVO.addInfo(new Date());
        return resultJsonVO;
    }

    @GetMapping("/bugTypes")
    public ResultJsonVO getBugTypes() {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        resultJsonVO.addInfo(Enums2ListUtils.parseBugType());
        return resultJsonVO;
    }

    @GetMapping("/codeLanguage")
    public ResultJsonVO getCodeLanguage() {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        resultJsonVO.addInfo(Enums2ListUtils.parseCodeLanguage());
        return resultJsonVO;
    }

    @GetMapping("/submitResult")
    public ResultJsonVO getSubmitResult() {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        resultJsonVO.addInfo(Enums2ListUtils.parseSubmitResult());
        return resultJsonVO;
    }
}

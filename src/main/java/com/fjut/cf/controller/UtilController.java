package com.fjut.cf.controller;

import com.fjut.cf.pojo.enums.ResultJsonCode;
import com.fjut.cf.pojo.vo.ResultJsonVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

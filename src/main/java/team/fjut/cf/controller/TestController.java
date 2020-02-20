package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.service.MallGoodsService;

/**
 * @author axiang [2019/10/21]
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    MallGoodsService mallGoodsService;


    @GetMapping("/test")
    public ResultJsonVO testMethod() {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        return resultJsonVO;
    }
}

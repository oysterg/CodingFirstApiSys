package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.component.redis.RedisUtils;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.service.MallGoodsService;
import team.fjut.cf.service.UserInfoService;
import team.fjut.cf.util.JsonFileUtils;

/**
 * @author axiang [2019/10/21]
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @Autowired
    MallGoodsService mallGoodsService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserInfoService userAuthService;

    @GetMapping("/test")
    public ResultJsonVO testMethod() {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        System.out.println(JsonFileUtils.isUsernameRestrict("axiangcoding"));
        return resultJsonVO;
    }
}

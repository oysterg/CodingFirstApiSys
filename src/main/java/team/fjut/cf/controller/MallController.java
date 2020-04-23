package team.fjut.cf.controller;

import org.springframework.web.bind.annotation.*;
import team.fjut.cf.config.interceptor.annotation.LoginRequired;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.MallGoods;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.MallGoodsService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author axiang [2019/11/12]
 */
@RestController
@CrossOrigin
@RequestMapping("/mall")
public class MallController {
    @Resource
    MallGoodsService mallGoodsService;

    @GetMapping("/list")
    public ResultJson getMallGoodsList(@RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        List<MallGoods> mallGoods = mallGoodsService.pages(pageNum, pageSize);
        Integer count = mallGoodsService.selectAllCount();
        resultJson.addInfo(mallGoods);
        resultJson.addInfo(count);
        return resultJson;
    }

    @LoginRequired
    @GetMapping("/info")
    public ResultJson getMallGoods(@RequestParam("id") Integer id) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        MallGoods mallGoods = mallGoodsService.selectByGoodsId(id);
        if (Objects.isNull(mallGoods)) {
            resultJson.setStatus(ResultCode.RESOURCE_NOT_EXIST);
        } else {
            resultJson.addInfo(mallGoods);
        }
        return resultJson;
    }

}


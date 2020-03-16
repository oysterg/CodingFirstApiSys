package team.fjut.cf.controller;

import team.fjut.cf.component.interceptor.LoginRequired;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.MallGoodsPO;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author axiang [2019/11/12]
 */
@RestController
@CrossOrigin
@RequestMapping("/mall")
public class MallController {
    @Autowired
    MallGoodsService mallGoodsService;

    @GetMapping("/list")
    public ResultJson getMallGoodsList(@RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        List<MallGoodsPO> mallGoodsPOS = mallGoodsService.pages(pageNum, pageSize);
        Integer count = mallGoodsService.selectAllCount();
        resultJson.addInfo(mallGoodsPOS);
        resultJson.addInfo(count);
        return resultJson;
    }

    @LoginRequired
    @GetMapping("/info")
    public ResultJson getMallGoods(@RequestParam("id") Integer id) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        MallGoodsPO mallGoodsPO = mallGoodsService.selectByGoodsId(id);
        if (Objects.isNull(mallGoodsPO)) {
            resultJson.setStatus(ResultCode.RESOURCE_NOT_EXIST);
        } else {
            resultJson.addInfo(mallGoodsPO);
        }
        return resultJson;
    }

}


package team.fjut.cf.controller;

import team.fjut.cf.component.interceptor.LoginRequired;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.MallGoodsPO;
import team.fjut.cf.pojo.vo.ResultJsonVO;
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
    public ResultJsonVO getMallGoodsList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        Integer startIndex = (pageNum - 1) * pageSize;
        List<MallGoodsPO> mallGoodsPOS = mallGoodsService.pages(startIndex, pageSize);
        Integer count = mallGoodsService.selectAllCount();
        resultJsonVO.addInfo(mallGoodsPOS);
        resultJsonVO.addInfo(count);
        return resultJsonVO;
    }

    @LoginRequired
    @GetMapping("/info")
    public ResultJsonVO getMallGoodsByGoodsId(@RequestParam("id") Integer id) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        MallGoodsPO mallGoodsPO = mallGoodsService.selectByGoodsId(id);
        if (Objects.isNull(mallGoodsPO)) {
            resultJsonVO.setStatus(ResultJsonCode.RESOURCE_NOT_EXIST);
        } else {
            resultJsonVO.addInfo(mallGoodsPO);
        }
        return resultJsonVO;
    }

}


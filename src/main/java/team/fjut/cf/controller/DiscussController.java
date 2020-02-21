package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.DiscussPostPO;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.service.DiscussPostService;

import java.util.List;

/**
 * @author axiang [2020/2/21]
 */
@RestController
@CrossOrigin
@RequestMapping("/discuss")
public class DiscussController {
    @Autowired
    DiscussPostService discussPostService;

    @GetMapping("/post/list")
    public ResultJsonVO getPostList(@RequestParam("pageNum") Integer pageNum,
                                    @RequestParam("pageSize") Integer pageSize) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        List<DiscussPostPO> discussPostPOS = discussPostService.pages(pageNum, pageSize);
        Integer count = discussPostService.allCount();
        if (discussPostPOS.size() != 0) {
            resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
            resultJsonVO.addInfo(discussPostPOS);
            resultJsonVO.addInfo(count);
        } else {
            resultJsonVO.setStatus(ResultJsonCode.RESOURCE_NOT_EXIST);
        }
        return resultJsonVO;
    }
}

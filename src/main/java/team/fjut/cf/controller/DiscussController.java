package team.fjut.cf.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.DiscussPostPO;
import team.fjut.cf.pojo.vo.DiscussPostVO;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.DiscussPostService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author axiang [2020/2/21]
 */
@RestController
@CrossOrigin
@RequestMapping("/discuss")
public class DiscussController {
    @Resource
    DiscussPostService discussPostService;

    @GetMapping("/post/list")
    public ResultJson getPostList(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        ResultJson resultJson = new ResultJson();
        List<DiscussPostPO> discussPostPOS = discussPostService.pages(pageNum, pageSize);
        Integer count = discussPostService.allCount();
        if (discussPostPOS.size() != 0) {
            resultJson.setStatus(ResultCode.REQUIRED_SUCCESS);
            resultJson.addInfo(discussPostPOS);
            resultJson.addInfo(count);
        } else {
            resultJson.setStatus(ResultCode.RESOURCE_NOT_EXIST);
        }
        return resultJson;
    }
}

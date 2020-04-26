package team.fjut.cf.controller;

import org.springframework.web.bind.annotation.*;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.vo.*;
import team.fjut.cf.service.BorderHonorRankService;
import team.fjut.cf.service.UserBaseInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
@RestController
@CrossOrigin
@RequestMapping("/border")
public class BorderController {
    @Resource
    BorderHonorRankService borderHonorRankService;

    @Resource
    UserBaseInfoService userBaseInfoService;

    // mod by zhongml [2020/4/26] 添加后台管理条件查询参数，按条件查询数量
    @GetMapping("/honor_rank")
    public ResultJson getHonorRankList(@RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize,
                                       @RequestParam(value = "sort", required = false) String sort,
                                       @RequestParam(value = "realName", required = false) String realName,
                                       @RequestParam(value = "awardLevel", required = false) Integer awardLevel,
                                       @RequestParam(value = "contestLevel", required = false) Integer contestLevel) {
        ResultJson resultJson = new ResultJson();
        List<BorderHonorRankVO> borderHonorRankVOS = borderHonorRankService.pages(pageNum, pageSize, sort, realName, awardLevel, contestLevel);
        Integer count = borderHonorRankService.countByCondition(realName, awardLevel, contestLevel);
        resultJson.addInfo(borderHonorRankVOS);
        resultJson.addInfo(count);
        return resultJson;
    }

    @GetMapping("/mini")
    public ResultJson getUserBorder(@RequestParam("pageNum") Integer pageNum,
                                    @RequestParam("pageSize") Integer pageSize) {
        ResultJson resultJson = new ResultJson();
        List<UserRatingBorderVO> userRatingBorderVOS = userBaseInfoService.selectRatingBorder(pageNum, pageSize);
        List<UserAcNumBorderVO> userAcNumBorderVOS = userBaseInfoService.selectAcNumBorder(pageNum, pageSize);
        List<UserAcbBorderVO> userAcbBorderVOS = userBaseInfoService.selectAcbBorder(pageNum, pageSize);
        resultJson.setStatus(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(userRatingBorderVOS);
        resultJson.addInfo(userAcNumBorderVOS);
        resultJson.addInfo(userAcbBorderVOS);
        return resultJson;
    }


}

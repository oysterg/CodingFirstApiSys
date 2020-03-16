package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.vo.*;
import team.fjut.cf.service.BorderHonorRankService;
import team.fjut.cf.service.UserInfoService;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
@RestController
@CrossOrigin
@RequestMapping("/border")
public class BorderController {
    @Autowired
    BorderHonorRankService borderHonorRankService;

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/honor_rank")
    public ResultJson getHonorRankList(@RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize) {
        ResultJson resultJson = new ResultJson();
        List<BorderHonorRankVO> borderHonorRankVOS = borderHonorRankService.pages(pageNum, pageSize);
        Integer count  = borderHonorRankService.selectAllCount();
        resultJson.addInfo(borderHonorRankVOS);
        resultJson.addInfo(count);
        return resultJson;
    }

    @GetMapping("/mini")
    public ResultJson getUserBorder(@RequestParam("pageNum") Integer pageNum,
                                    @RequestParam("pageSize") Integer pageSize) {
        ResultJson resultJson = new ResultJson();
        List<UserRatingBorderVO> userRatingBorderVOS = userInfoService.selectRatingBorder(pageNum, pageSize);
        List<UserAcNumBorderVO> userAcNumBorderVOS = userInfoService.selectAcNumBorder(pageNum, pageSize);
        List<UserAcbBorderVO> userAcbBorderVOS = userInfoService.selectAcbBorder(pageNum, pageSize);
        resultJson.setStatus(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(userRatingBorderVOS);
        resultJson.addInfo(userAcNumBorderVOS);
        resultJson.addInfo(userAcbBorderVOS);
        return resultJson;
    }


}

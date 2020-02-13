package com.fjut.cf.controller;

import com.fjut.cf.pojo.enums.ResultJsonCode;
import com.fjut.cf.pojo.vo.*;
import com.fjut.cf.service.BorderHonorRankService;
import com.fjut.cf.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultJsonVO getHonorRankList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        Integer startIndex = (pageNum - 1) * pageSize;
        List<BorderHonorRankVO> borderHonorRankVOS = borderHonorRankService.pages(startIndex, pageSize);
        Integer count  = borderHonorRankService.selectAllCount();
        resultJsonVO.addInfo(borderHonorRankVOS);
        resultJsonVO.addInfo(count);
        return resultJsonVO;
    }

    @GetMapping("/mini/get")
    public ResultJsonVO getUserBorder(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        Integer startIndex = (pageNum - 1) * pageSize;
        List<UserRatingBorderVO> userRatingBorderVOS = userInfoService.selectRatingBorder(startIndex, pageSize);
        List<UserAcNumBorderVO> userAcNumBorderVOS = userInfoService.selectAcNumBorder(startIndex, pageSize);
        List<UserAcbBorderVO> userAcbBorderVOS = userInfoService.selectAcbBorder(startIndex, pageSize);
        resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
        resultJsonVO.addInfo(userRatingBorderVOS);
        resultJsonVO.addInfo(userAcNumBorderVOS);
        resultJsonVO.addInfo(userAcbBorderVOS);
        return resultJsonVO;
    }


}

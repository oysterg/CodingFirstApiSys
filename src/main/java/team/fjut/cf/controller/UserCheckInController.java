package team.fjut.cf.controller;

import team.fjut.cf.component.interceptor.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.UserCheckInPO;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.service.UserCheckInService;
import team.fjut.cf.util.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author axiang [2019/11/12]
 */
@RestController
@CrossOrigin
@RequestMapping("/check_in")
public class UserCheckInController {
    @Autowired
    UserCheckInService userCheckInService;

    @PrivateRequired
    @GetMapping("/list")
    public ResultJsonVO getUserCheckIn(@RequestParam("username") String username,
                                       @RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        if (pageNum == null) {
            pageNum = 0;
        }
        if (pageSize == null) {
            pageSize = 30;
        }
        // 这里一次性查询全部签到记录，暂不用到分页
        List<UserCheckInPO> userCheckInPOS = userCheckInService.selectByUsername(username);
        resultJsonVO.addInfo(userCheckInPOS);
        return resultJsonVO;
    }

    @PrivateRequired
    @PostMapping("/check")
    public ResultJsonVO userCheckIn(HttpServletRequest request, String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        UserCheckInPO userCheckInPO = new UserCheckInPO();
        userCheckInPO.setUsername(username);
        userCheckInPO.setIpAddress(IpUtils.getClientIpAddress(request));
        userCheckInPO.setCheckTime(new Date());
        userCheckInPO.setInfo("日常签到");
        Integer count = userCheckInService.insert(userCheckInPO);
        if (1 == count) {
            resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS, "签到成功");
        } else {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "签到失败");
        }
        return resultJsonVO;
    }

    @PrivateRequired
    @GetMapping("/is_checked")
    public ResultJsonVO getUserIsChecked(@RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        Integer count = userCheckInService.selectCountTodayCheckInByUsername(username);
        if (0 == count) {
            resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS, "今天未签到");
        } else {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "今天已签到 " + count + " 次");
        }
        return resultJsonVO;
    }

}

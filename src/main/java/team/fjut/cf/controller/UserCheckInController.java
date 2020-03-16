package team.fjut.cf.controller;

import team.fjut.cf.component.interceptor.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.UserCheckIn;
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
@RequestMapping("/user/checkIn")
public class UserCheckInController {
    @Autowired
    UserCheckInService userCheckInService;

    @PrivateRequired
    @PostMapping("/list")
    public ResultJsonVO getUserCheckIn(@RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        List<UserCheckIn> userCheckIns = userCheckInService.select(username);
        resultJsonVO.addInfo(userCheckIns);
        return resultJsonVO;
    }

    @PrivateRequired
    @PostMapping("/check")
    public ResultJsonVO userCheckIn(HttpServletRequest request, String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        UserCheckIn userCheckIn = new UserCheckIn();
        userCheckIn.setUsername(username);
        userCheckIn.setIpAddress(IpUtils.getClientIpAddress(request));
        userCheckIn.setCheckTime(new Date());
        userCheckIn.setInfo("日常签到");
        Integer count = userCheckInService.insert(userCheckIn);
        if (1 == count) {
            resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS, "签到成功");
        } else {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "签到失败");
        }
        return resultJsonVO;
    }

    @PrivateRequired
    @GetMapping("/isChecked")
    public ResultJsonVO getUserIsChecked(@RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        boolean todayUserCheckIn = userCheckInService.isTodayUserCheckIn(username);
        resultJsonVO.addInfo(todayUserCheckIn);
        return resultJsonVO;
    }

}

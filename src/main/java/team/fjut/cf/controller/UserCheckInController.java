package team.fjut.cf.controller;

import org.springframework.web.bind.annotation.*;
import team.fjut.cf.config.interceptor.annotation.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.UserCheckIn;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.UserCheckInService;
import team.fjut.cf.utils.IpUtils;

import javax.annotation.Resource;
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
    @Resource
    UserCheckInService userCheckInService;

    @PrivateRequired
    @PostMapping("/list")
    public ResultJson getUserCheckIn(@RequestParam("username") String username) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        List<UserCheckIn> userCheckIns = userCheckInService.select(username);
        resultJson.addInfo(userCheckIns);
        return resultJson;
    }

    @PrivateRequired
    @PostMapping("/check")
    public ResultJson userCheckIn(HttpServletRequest request, String username) {
        ResultJson resultJson = new ResultJson();
        UserCheckIn userCheckIn = new UserCheckIn();
        userCheckIn.setUsername(username);
        userCheckIn.setIpAddress(IpUtils.getClientIpAddress(request));
        userCheckIn.setCheckTime(new Date());
        userCheckIn.setInfo("日常签到");
        Integer count = userCheckInService.insert(userCheckIn);
        if (1 == count) {
            resultJson.setStatus(ResultCode.REQUIRED_SUCCESS, "签到成功");
        } else {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL, "签到失败");
        }
        return resultJson;
    }

    @PrivateRequired
    @GetMapping("/isChecked")
    public ResultJson getUserIsChecked(@RequestParam("username") String username) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        boolean todayUserCheckIn = userCheckInService.isTodayUserCheckIn(username);
        resultJson.addInfo(todayUserCheckIn);
        return resultJson;
    }

}

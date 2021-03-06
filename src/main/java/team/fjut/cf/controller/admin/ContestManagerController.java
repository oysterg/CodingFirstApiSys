package team.fjut.cf.controller.admin;

import com.alibaba.druid.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.ContestInfoPO;
import team.fjut.cf.pojo.po.ContestRegisterUserPO;
import team.fjut.cf.pojo.vo.ContestRegisterUserVO;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.ContestInfoService;
import team.fjut.cf.service.ContestRegisterService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zhongml [2020/4/23]
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/contest")
public class ContestManagerController {

    @Resource
    ContestRegisterService contestRegisterService;

    @Resource
    ContestInfoService contestInfoService;

    @GetMapping("/review/list")
    public ResultJson getContestLimit(@RequestParam("page") Integer page,
                                     @RequestParam("limit") Integer limit,
                                     @RequestParam(value = "sort", required = false) String sort,
                                     @RequestParam(value = "contestKind", required = false) Integer contestKind,
                                     @RequestParam(value = "reviewStatus", required = false) Integer reviewStatus,
                                     @RequestParam(value = "username", required = false) String username) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        if (StringUtils.isEmpty(username)) {
            username = null;
        } else {
            username = "%" + username + "%";
        }
        List<ContestRegisterUserVO> contestRegisterUserVOS = contestRegisterService.pagesByConditions(page, limit, sort, contestKind, reviewStatus, username);
        Integer count = contestRegisterService.selectCountByConditions(contestKind, reviewStatus, username);
        resultJson.addInfo(contestRegisterUserVOS);
        resultJson.addInfo(count);
        return resultJson;
    }

    @PutMapping("/review/update")
    public ResultJson updateContest(@RequestParam("id") Integer id,
                                     @RequestParam("reviewStatus") Integer reviewStatus,
                                     @RequestParam("reviewInfo") String reviewInfo) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        ContestRegisterUserPO contestRegisterUserPO = new ContestRegisterUserPO();
        contestRegisterUserPO.setId(id);
        contestRegisterUserPO.setReviewStatus(reviewStatus);
        contestRegisterUserPO.setReviewTime(new Date());
        contestRegisterUserPO.setReviewInfo(reviewInfo);
        int result  = contestRegisterService.updateReviewStatus(contestRegisterUserPO);
        if (result != 1) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }

    @PostMapping("/review/create")
    public ResultJson createContest(@RequestParam("data") ContestInfoPO contestInfoPO) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        int result  = contestInfoService.createContest(contestInfoPO);
        if (result != 1) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }
}

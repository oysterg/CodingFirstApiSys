package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.interceptor.LoginRequired;
import team.fjut.cf.component.interceptor.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.*;
import team.fjut.cf.pojo.vo.ProblemListVO;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.pojo.vo.UserRadarVO;
import team.fjut.cf.service.*;

import java.util.List;

/**
 * @author axiang [2019/10/22]
 */
@RestController
@RequestMapping("/problem")
@CrossOrigin
public class ProblemController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    ProblemService problemService;

    @Autowired
    ProblemTagService problemTagService;

    @Autowired
    UserProblemSolvedService userProblemSolvedService;

    @Autowired
    ViewProblemInfoService viewProblemInfoService;

    /**
     * @param pageNum
     * @param pageSize
     * @param problemId
     * @param tagId
     * @param title
     * @param username
     * @return
     */
    @GetMapping("/list")
    public ResultJsonVO getProblemLimit(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam(value = "problemId", required = false) Integer problemId,
                                        @RequestParam(value = "tagId", required = false) Integer tagId,
                                        @RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "username", required = false) String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        if (pageNum == null) {
            pageNum = 0;
        }
        if (pageSize == null || pageSize > 100) {
            pageSize = 50;
        }
        if (!StringUtils.isEmpty(title)) {
            // 拼接查询字符串
            title = "%" + title + "%";
        } else {
            // 拼接查询字符串如果为空字符或者null则 置为null
            title = null;
        }
        List<ProblemListVO> problemLists = viewProblemInfoService.pagesByConditions(pageNum, pageSize, problemId, title, tagId, username);
        int count = viewProblemInfoService.countByConditions(problemId, title, tagId);
        resultJsonVO.addInfo(problemLists);
        resultJsonVO.addInfo(count);
        return resultJsonVO;
    }

    @GetMapping("/info")
    public ResultJsonVO getProblemInfo(@RequestParam(value = "username", required = false) String username,
                                       @RequestParam("problemId") Integer problemId) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        ProblemInfo problemInfo = problemService.selectProblemInfoByProblemId(problemId);
        ProblemViewPO problemViewPO = problemService.selectProblemViewByProblemId(problemId);
        List<ProblemSamplePO> problemSamplePOS = problemService.selectProblemSampleByProblemId(problemId);
        UserProblemSolved userProblemSolved = userProblemSolvedService.selectCountByUsernameAndProblemId(username, problemId);
        Boolean isSolved = userProblemSolved != null && userProblemSolved.getSolvedCount() > 0;
        resultJsonVO.addInfo(problemInfo);
        resultJsonVO.addInfo(problemViewPO);
        resultJsonVO.addInfo(problemSamplePOS);
        resultJsonVO.addInfo(isSolved);
        return resultJsonVO;
    }

    @LoginRequired
    @GetMapping("/radar")
    public ResultJsonVO getProblemRadar(@RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        List<UserRadarVO> userRadarVOS = problemService.selectUserProblemRadarByUsername(username);
        resultJsonVO.addInfo(userRadarVOS);
        return resultJsonVO;
    }

    @PrivateRequired
    @GetMapping("/recommend")
    public ResultJsonVO getRecommendProblem(@RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        Boolean isExist = userInfoService.selectExistByUsername(username);
        if (!isExist) {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "用户不存在");
            return resultJsonVO;
        }
        List<ProblemInfo> problems = problemService.selectRecommendProblemsByUsername(username);
        resultJsonVO.addInfo(problems);
        return resultJsonVO;
    }


}

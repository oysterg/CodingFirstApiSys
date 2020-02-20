package team.fjut.cf.controller;

import team.fjut.cf.component.interceptor.LoginRequired;
import team.fjut.cf.component.interceptor.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.ProblemInfoPO;
import team.fjut.cf.pojo.po.ProblemSamplePO;
import team.fjut.cf.pojo.po.ProblemViewPO;
import team.fjut.cf.pojo.po.UserProblemSolvedPO;
import team.fjut.cf.pojo.vo.ProblemListVO;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.pojo.vo.UserRadarVO;
import team.fjut.cf.service.ProblemService;
import team.fjut.cf.service.ProblemTagService;
import team.fjut.cf.service.UserInfoService;
import team.fjut.cf.service.UserProblemSolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author axiang [2019/10/22]
 */
@RestController
@RequestMapping("/problem")
@CrossOrigin
public class LocalProblemController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    ProblemService problemService;

    @Autowired
    ProblemTagService problemTagService;

    @Autowired
    UserProblemSolvedService userProblemSolvedService;

    @GetMapping("/list")
    public ResultJsonVO getProblemLimit(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam(value = "tagId", required = false) Integer tagId,
                                        @RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "username", required = false) String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        if (pageNum == null) {
            pageNum = 0;
        }
        if (pageSize == null) {
            pageSize = 50;
        }
        if (!StringUtils.isEmpty(title)) {
            // 拼接查询字符串
            title = "%" + title + "%";
        } else {
            // 拼接查询字符串如果为空字符或者null则 置为null
            title = null;
        }
        List<ProblemListVO> problemList = problemService.pagesByConditions(username, title, tagId, pageNum, pageSize);
        Integer integer = problemService.selectCountByConditions(title, tagId);
        resultJsonVO.addInfo(problemList);
        resultJsonVO.addInfo(integer);
        resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
        return resultJsonVO;
    }

    @GetMapping("/info")
    public ResultJsonVO getProblemInfoByProblemId(@RequestParam(value = "username", required = false) String username,
                                                  @RequestParam("problemId") Integer problemId) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        ProblemInfoPO problemInfoPO = problemService.selectProblemInfoByProblemId(problemId);
        ProblemViewPO problemViewPO = problemService.selectProblemViewByProblemId(problemId);
        List<ProblemSamplePO> problemSamplePOS = problemService.selectProblemSampleByProblemId(problemId);
        UserProblemSolvedPO userProblemSolved = userProblemSolvedService.selectCountByUsernameAndProblemId(username, problemId);
        Boolean isSolved = userProblemSolved != null && userProblemSolved.getSolvedCount() > 0;
        resultJsonVO.addInfo(problemInfoPO);
        resultJsonVO.addInfo(problemViewPO);
        resultJsonVO.addInfo(problemSamplePOS);
        resultJsonVO.addInfo(isSolved);
        return resultJsonVO;
    }

    @LoginRequired
    @GetMapping("/radar")
    public ResultJsonVO getProblemRadarByUsername(@RequestParam("username") String username) {
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
        List<ProblemInfoPO> problems = problemService.selectRecommendProblemsByUsername(username);
        resultJsonVO.addInfo(problems);
        return resultJsonVO;
    }


}

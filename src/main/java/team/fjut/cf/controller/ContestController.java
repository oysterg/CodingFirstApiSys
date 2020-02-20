package team.fjut.cf.controller;

import team.fjut.cf.component.interceptor.LoginRequired;
import team.fjut.cf.pojo.enums.CodeLanguage;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.enums.SubmitResult;
import team.fjut.cf.pojo.po.ContestInfoPO;
import team.fjut.cf.pojo.po.ContestProblemPO;
import team.fjut.cf.pojo.vo.ContestListVO;
import team.fjut.cf.pojo.vo.JudgeStatusVO;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.service.ContestInfoService;
import team.fjut.cf.service.ContestProblemService;
import team.fjut.cf.service.JudgeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author axiang [2019/11/18]
 */
@RestController
@CrossOrigin
@RequestMapping("/contest")
public class ContestController {
    @Autowired
    ContestInfoService contestInfoService;

    @Autowired
    ContestProblemService contestProblemService;

    @Autowired
    JudgeStatusService judgeStatusService;

    @GetMapping("/list")
    public ResultJsonVO getContestList(@RequestParam("kind") Integer kind,
                                       @RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize,
                                       @RequestParam(value = "searchName", required = false) String searchName,
                                       @RequestParam(value = "searchPermission", required = false) Integer searchPermission,
                                       @RequestParam(value = "searchStatus", required = false) Integer searchStatus) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        if (StringUtils.isEmpty(searchName)) {
            searchName = null;
        } else {
            searchName = "%" + searchName + "%";
        }
        List<ContestListVO> contestListVOS = contestInfoService.pagesByConditions(kind, searchName, searchPermission, searchStatus, pageNum, pageSize);
        Integer count = contestInfoService.selectCountByConditions(kind, searchName, searchPermission, searchStatus);
        resultJsonVO.addInfo(contestListVOS);
        resultJsonVO.addInfo(count);
        return resultJsonVO;
    }

    @LoginRequired
    @GetMapping("/info")
    public ResultJsonVO getContestInfo(@RequestParam("contestId") Integer contestId,
                                       @RequestParam(value = "username", required = false) String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        ContestInfoPO contestInfoPO = contestInfoService.selectByContestId(contestId);
        resultJsonVO.addInfo(contestInfoPO);
        return resultJsonVO;
    }

    @LoginRequired
    @GetMapping("/problem/list")
    public ResultJsonVO getContestProblemList(@RequestParam("contestId") Integer contestId) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        List<ContestProblemPO> contestProblemPOS = contestProblemService.selectByContestId(contestId);
        resultJsonVO.addInfo(contestProblemPOS);
        return resultJsonVO;
    }

    @LoginRequired
    @GetMapping("/status/list")
    public ResultJsonVO getContestStatusList(@RequestParam("contestId") Integer contestId,
                                             @RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("pageSize") Integer pageSize,
                                             @RequestParam(value = "nick", required = false) String nick,
                                             @RequestParam(value = "problemId", required = false) Integer problemId,
                                             @RequestParam(value = "result", required = false) String resultStr,
                                             @RequestParam(value = "language", required = false) String languageStr) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = 30;
        }
        if (!StringUtils.isEmpty(nick)) {
            nick = "%" + nick + "%";
        } else {
            nick = null;
        }
        Integer result = null;
        Integer language = null;
        if (StringUtils.isEmpty(problemId)) {
            problemId = null;
        }
        if (!StringUtils.isEmpty(resultStr)) {
            result = SubmitResult.getCodeByName(resultStr);
        }
        if (!StringUtils.isEmpty(languageStr)) {
            language = CodeLanguage.getCodeByName(languageStr);
        }
        List<JudgeStatusVO> judgeStatusVOS = judgeStatusService.pagesByConditions(pageNum, pageSize, contestId, nick, problemId, result, language);
        Integer length = judgeStatusService.selectCountByConditions(contestId, nick, problemId, result, language);
        resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
        resultJsonVO.addInfo(judgeStatusVOS);
        resultJsonVO.addInfo(length);
        return resultJsonVO;
    }

}

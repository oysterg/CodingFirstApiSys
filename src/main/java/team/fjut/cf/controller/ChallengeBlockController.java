package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.interceptor.LoginRequired;
import team.fjut.cf.component.interceptor.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.ChallengeBlockConditionPO;
import team.fjut.cf.pojo.vo.*;
import team.fjut.cf.service.ChallengeBlockProblemService;
import team.fjut.cf.service.ChallengeBlockService;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
@RestController
@CrossOrigin
@RequestMapping("/challenge_block")
public class ChallengeBlockController {
    @Autowired
    ChallengeBlockService challengeBlockService;

    @Autowired
    ChallengeBlockProblemService challengeBlockProblemService;


    @PrivateRequired
    @GetMapping("/graph")
    public ResultJsonVO getOpenedChallengeBlock(@RequestParam("username") String username) {

        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        List<UserChallengeBlockVO> userChallengeBlockVOS = challengeBlockService.selectByUsername(username);
        List<ChallengeBlockConditionPO> challengeBlockConditionPOS = challengeBlockService.selectConditions();
        resultJsonVO.addInfo(userChallengeBlockVOS);
        resultJsonVO.addInfo(challengeBlockConditionPOS);
        return resultJsonVO;
    }

    @LoginRequired
    @GetMapping("/condition")
    public ResultJsonVO getBlockCondition(@RequestParam("blockId") Integer blockId) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        List<ChallengeBlockConditionVO> conditions = challengeBlockService.selectConditionByBlockId(blockId);
        resultJsonVO.addInfo(conditions);
        return resultJsonVO;
    }

    @PrivateRequired
    @GetMapping("/detail")
    public ResultJsonVO getChallengeBlockDetail(@RequestParam("blockId") Integer blockId,
                                                @RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        ChallengeBlockVO challengeBlockVO = challengeBlockService.selectByBlockIdAndUsername(blockId, username);
        List<ChallengeBlockConditionVO> conditionVOS = challengeBlockService.selectConditionByBlockId(blockId);
        resultJsonVO.addInfo(challengeBlockVO);
        resultJsonVO.addInfo(conditionVOS);
        return resultJsonVO;
    }

    @PrivateRequired
    @GetMapping("/problem")
    public ResultJsonVO getBlockProblems(@RequestParam("username") String username,
                                         @RequestParam("blockId") Integer blockId,
                                         @RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        List<ChallengeBlockProblemVO> challengeBlockProblemVOS = challengeBlockProblemService.pagesByBlockId(username, blockId, pageNum, pageSize);
        Integer count = challengeBlockProblemService.selectCountByBlockId(blockId);
        if (challengeBlockProblemVOS.size() == 0 && count == 0) {
            resultJsonVO.setStatus(ResultJsonCode.RESOURCE_NOT_EXIST, "本模块没有题目");
        }
        resultJsonVO.addInfo(challengeBlockProblemVOS);
        resultJsonVO.addInfo(count);
        return resultJsonVO;
    }

}

package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.config.interceptor.LoginRequired;
import team.fjut.cf.config.interceptor.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultCode;
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
    public ResultJson getOpenedChallengeBlock(@RequestParam("username") String username) {

        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        List<UserChallengeBlockVO> userChallengeBlockVOS = challengeBlockService.selectByUsername(username);
        List<ChallengeBlockConditionPO> challengeBlockConditionPOS = challengeBlockService.selectConditions();
        resultJson.addInfo(userChallengeBlockVOS);
        resultJson.addInfo(challengeBlockConditionPOS);
        return resultJson;
    }

    @LoginRequired
    @GetMapping("/condition")
    public ResultJson getBlockCondition(@RequestParam("blockId") Integer blockId) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        List<ChallengeBlockConditionVO> conditions = challengeBlockService.selectConditionByBlockId(blockId);
        resultJson.addInfo(conditions);
        return resultJson;
    }

    @PrivateRequired
    @GetMapping("/detail")
    public ResultJson getChallengeBlockDetail(@RequestParam("blockId") Integer blockId,
                                              @RequestParam("username") String username) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        ChallengeBlockVO challengeBlockVO = challengeBlockService.selectByBlockIdAndUsername(blockId, username);
        List<ChallengeBlockConditionVO> conditionVOS = challengeBlockService.selectConditionByBlockId(blockId);
        resultJson.addInfo(challengeBlockVO);
        resultJson.addInfo(conditionVOS);
        return resultJson;
    }

    @PrivateRequired
    @GetMapping("/problem")
    public ResultJson getBlockProblems(@RequestParam("username") String username,
                                       @RequestParam("blockId") Integer blockId,
                                       @RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        List<ChallengeBlockProblemVO> challengeBlockProblemVOS = challengeBlockProblemService.pagesByBlockId(username, blockId, pageNum, pageSize);
        Integer count = challengeBlockProblemService.selectCountByBlockId(blockId);
        if (challengeBlockProblemVOS.size() == 0 && count == 0) {
            resultJson.setStatus(ResultCode.RESOURCE_NOT_EXIST, "本模块没有题目");
        }
        resultJson.addInfo(challengeBlockProblemVOS);
        resultJson.addInfo(count);
        return resultJson;
    }

}

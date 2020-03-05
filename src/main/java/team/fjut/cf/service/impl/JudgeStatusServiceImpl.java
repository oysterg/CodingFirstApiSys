package team.fjut.cf.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.fjut.cf.component.judge.local.LocalJudgeHttpClient;
import team.fjut.cf.mapper.*;
import team.fjut.cf.pojo.enums.SubmitResult;
import team.fjut.cf.pojo.po.JudgeResultPO;
import team.fjut.cf.pojo.po.JudgeStatusPO;
import team.fjut.cf.pojo.po.UserProblemSolved;
import team.fjut.cf.pojo.vo.JudgeStatus;
import team.fjut.cf.pojo.vo.StatusCountVO;
import team.fjut.cf.service.ChallengeBlockService;
import team.fjut.cf.service.JudgeStatusService;

import java.util.Date;
import java.util.List;

/**
 * @author axiang [2019/10/30]
 */
@Service
public class JudgeStatusServiceImpl implements JudgeStatusService {
    @Autowired
    UserBaseInfoMapper userBaseInfoMapper;

    @Autowired
    ProblemInfoMapper problemInfoMapper;

    @Autowired
    JudgeStatusMapper judgeStatusMapper;

    @Autowired
    JudgeResultMapper judgeResultMapper;

    @Autowired
    ViewJudgeStatusMapper viewJudgeStatusMapper;

    @Autowired
    UserProblemSolvedMapper userProblemSolvedMapper;

    @Autowired
    ChallengeBlockService challengeBlockService;

    @Autowired
    LocalJudgeHttpClient localJudgeHttpClient;

    @Override
    public Integer insert(JudgeStatusPO judgeStatusPO) {
        return judgeStatusMapper.insert(judgeStatusPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateIfSubmitSuccess(JudgeStatusPO judgeStatusPO) {
        String username = judgeStatusPO.getUsername();
        Integer problemId = judgeStatusPO.getProblemId();
        // 题目ID的题目提交总数+1
        problemInfoMapper.updateTotalSubmitAddOne(problemId);
        Integer count = judgeStatusMapper.selectCountByUsernameAndProblemId(username, problemId);
        // 如果用户是第一次提交该题，则题目的总提交用户数+1
        if (count == 1) {
            problemInfoMapper.updateTotalSubmitUserAddOne(problemId);
        }
        // 查询解答记录表中该题的解答记录是否存在
        Integer countSolvedRecord = userProblemSolvedMapper.selectCountByUsernameAndProblemId(username, problemId);
        if (countSolvedRecord == 0) {
            UserProblemSolved userProblemSolved = new UserProblemSolved();
            userProblemSolved.setUsername(username);
            userProblemSolved.setProblemId(problemId);
            userProblemSolved.setTryCount(1);
            userProblemSolved.setSolvedCount(0);
            userProblemSolved.setLastTryTime(new Date());
            userProblemSolvedMapper.insert(userProblemSolved);
        } else {
            // 尝试次数+1
            userProblemSolvedMapper.updateTryCountAddOne(username, problemId);
            //
            userProblemSolvedMapper.updateLastTryTime(username, problemId);
        }
        return true;
    }

    @Override
    public Boolean updateIfSubmitFail(Integer judgeId) {
        // 更新评测记录中的评测结果为ERROR （提交失败）
        judgeStatusMapper.updateResultById(judgeId, SubmitResult.ERROR.getCode());
        // 更新评测结果内容
        JudgeResultPO judgeResultPO = new JudgeResultPO();
        judgeResultPO.setJudgeId(judgeId);
        judgeResultPO.setInfo("Submit to local judge system fail at " + new Date().toString());
        judgeResultMapper.insert(judgeResultPO);
        return true;
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void selectFromLocalJudgeAsync(JudgeStatusPO judgeStatusPO) throws Exception {
        // 评测结果
        String judgingStr = "judging";
        boolean quitLoop = false;
        int times = 100;
        // TODO: 可以根据活跃线程数executor.getActiveCount()来设置获取时间间隔times
        do {
            JSONObject jsonObject = localJudgeHttpClient.getResultFromLocalJudge(judgeStatusPO.getId());
            if ("success".equals(jsonObject.getString("ret"))) {
                JSONObject resultJsonObj = JSONObject.parseObject(jsonObject.getString("result"));
                judgingStr = resultJsonObj.getString("type");
                // 如果代码在评测等待中，则更新评测结果为评测等待
                if ("padding".equals(judgingStr)) {
                    judgeStatusMapper.updateResultById(judgeStatusPO.getId(), SubmitResult.PENDING.getCode());
                }
                // 如果代码在评测中，则更新状态
                else if ("judging".equals(judgingStr)) {
                    judgeStatusMapper.updateResultById(judgeStatusPO.getId(), SubmitResult.JUDGING.getCode());
                }
                // 如果代码评测结果为编译失败，则更新状态
                else if ("CE".equals(judgingStr)) {
                    // 插入数据库表中评测结果为CE
                    JudgeResultPO judgeResultPO = new JudgeResultPO();
                    judgeResultPO.setJudgeId(judgeStatusPO.getId());
                    judgeResultPO.setInfo(resultJsonObj.getString("info"));
                    judgeResultMapper.insert(judgeResultPO);
                    judgeStatusMapper.updateResultById(judgeStatusPO.getId(), SubmitResult.CE.getCode());
                    // 退出循环
                    quitLoop = true;
                }
                //以下为编译正确返回的内容，即提交并且编译成功得到的结果，但不一定为AC
                else {
                    JSONArray retJsonArr = resultJsonObj.getJSONArray("ret");
                    // 评测机返回多组不同IO的评测记录，交由处理反馈结果
                    judgingStr = handleLocalJudgeReturns(retJsonArr, judgeStatusPO);
                    quitLoop = true;
                }
            } else {
                quitLoop = true;
            }
            Thread.sleep(2000);
            times--;
        } while (times > 0 && !quitLoop);
        // 200s的获取结果执行完毕或者拿到AC/CE编译的结果后执行
        String username = judgeStatusPO.getUsername();
        Integer problemId = judgeStatusPO.getProblemId();
        // 如果用户AC了这道题，执行的逻辑
        // FIXME: SCORE的题目没有计入数据，需要修BUG
        if ("AC".equalsIgnoreCase(judgingStr)) {
            // 题目 AC 数量加一
            problemInfoMapper.updateTotalAcAddOne(problemId);
            // 查询用户以前是否解决过该题
            Integer count = judgeStatusMapper.selectCountIfAcByUsernameAndProblemId(username, problemId);
            // 如果是第一次解决该题
            if (count == 1) {
                // 用户AC数量加一
                userBaseInfoMapper.updateAcNumAddOneByUsername(username);
                // 题目AC用户数量加一
                problemInfoMapper.updateTotalAcUserAddOne(problemId);
            }
            /* 更新题目解决表 */
            // 解题ac次数加一
            userProblemSolvedMapper.updateSolvedCountAddOne(username, problemId);
            // 查询题目解决表中是否有记录
            UserProblemSolved userSolvedRecord = userProblemSolvedMapper.selectByUsernameAndProblemId(username, problemId);
            // 如果第一次解答该题
            if (userSolvedRecord.getSolvedCount() == 1) {
                // 更新第一次解决时间
                userProblemSolvedMapper.updateFirstSolvedTime(username, problemId);
            }
            // 挑战模式的更新逻辑
            challengeBlockService.updateOpenBlock(username, problemId);
        }
        // 用户尝试过该题目，但没有解决
        else {
        }
    }

    /**
     * 处理本地评测机返回值的业务
     *
     * @param retJsonArr
     * @param judgeStatusPO
     * @return
     */
    private String handleLocalJudgeReturns(JSONArray retJsonArr, JudgeStatusPO judgeStatusPO) {
        JudgeResultPO judgeResultPO = new JudgeResultPO();
        String ans;
        String resultStr = "";
        String resStatus = "";
        int time = 0;
        int memory = 0;
        // 如果是计分类题，获得的得分
        int getScore = 0;
        // 是否是计分类题
        boolean isScore = false;
        for (int i = 0; i < retJsonArr.size(); i++) {
            resStatus = retJsonArr.getJSONArray(i).getString(1);
            resultStr += ("测试结果：【" + resStatus + "】 ");
            resultStr += ("测试文件：【" + retJsonArr.getJSONArray(i).getString(0) + "】 ");
            if ("SC".equals(resStatus)) {
                int score = retJsonArr.getJSONArray(i).getInteger(5);
                getScore += score;
                resultStr += ("得分：【" + score + "】 ");
                isScore = true;

                time += retJsonArr.getJSONArray(i).getInteger(2);
                resultStr += ("用时：【" + retJsonArr.getJSONArray(i).getInteger(2) + "MS】 ");
            } else if ("MLE".equals(resStatus) || "OLE".equals(resStatus)) {
                time += retJsonArr.getJSONArray(i).getInteger(4);
                resultStr += ("用时：【" + retJsonArr.getJSONArray(i).getInteger(4) + "MS】 ");
            } else {
                time += retJsonArr.getJSONArray(i).getInteger(2);
                resultStr += ("用时：【" + retJsonArr.getJSONArray(i).getInteger(2) + "MS】 ");
            }
            memory = Math.max(memory, retJsonArr.getJSONArray(i).getInteger(3));
            resultStr += ("内存：【" + retJsonArr.getJSONArray(i).getInteger(3) + "KB】\n");
        }
        judgeResultPO.setInfo(resultStr);
        judgeResultPO.setJudgeId(judgeStatusPO.getId());
        judgeResultMapper.insert(judgeResultPO);
        // 如果是得分题
        if (isScore) {
            // 设置得分
            judgeStatusPO.setScore(getScore / retJsonArr.size());
            // 设置结果
            judgeStatusPO.setResult(SubmitResult.SC.getCode());
            ans = "SC";
        } else {
            judgeStatusPO.setResult(SubmitResult.valueOf(resStatus).getCode());
            ans = resStatus;
        }
        judgeStatusPO.setTimeUsed(time + "MS");
        judgeStatusPO.setMemoryUsed(memory + "KB");
        judgeStatusMapper.updateAfterJudgedById(judgeStatusPO);
        return ans;
    }

    @Override
    public List<StatusCountVO> selectCountByDay(int days) {
        List<StatusCountVO> statusCountVOS = judgeStatusMapper.selectCountByDay(days);
        return judgeStatusMapper.selectCountByDay(days);
    }

    @Override
    public Integer selectCountByConditions(Integer contestId, String nick, Integer problemId, Integer result, Integer language) {
        return null;
    }

    @Override
    public JudgeStatus selectAsViewJudgeStatusById(Integer id) {
        // FIXME:这里需要修改
        return null;
        //ViewJudgeStatus viewJudgeStatus = viewJudgeStatusMapper.queryById(id);
        //JudgeStatus judgeStatus = new JudgeStatus();
        //judgeStatus.setUsername(viewJudgeStatus.getUsername());
        //// 如果是Score的，带上分值
        //if (viewJudgeStatus.getResult() == SubmitResult.SC.getCode()) {
        //    judgeStatus.setResult(SubmitResult.getNameByCode(viewJudgeStatus.getResult()) + " " + viewJudgeStatus.getScore());
        //} else {
        //    judgeStatus.setResult(SubmitResult.getNameByCode(viewJudgeStatus.getResult()));
        //}
        //judgeStatus.setSubmitTime(viewJudgeStatus.getSubmitTime());
        //judgeStatus.setProblemId(viewJudgeStatus.getProblemId());
        //judgeStatus.setMemoryUsed(viewJudgeStatus.getMemoryUsed());
        //judgeStatus.setTimeUsed(viewJudgeStatus.getTimeUsed());
        //judgeStatus.setNick(viewJudgeStatus.getNick());
        //judgeStatus.setCode(viewJudgeStatus.getCode());
        //judgeStatus.setCodeLength(viewJudgeStatus.getCodeLength());
        //judgeStatus.setLanguage(CodeLanguage.getNameByCode(viewJudgeStatus.getLanguage()));
        //judgeStatus.setId(viewJudgeStatus.getId());
        //return judgeStatus;
    }

    @Override
    public Integer selectCountByUsername(String username) {
        return judgeStatusMapper.selectCountByUsername(username);
    }


}

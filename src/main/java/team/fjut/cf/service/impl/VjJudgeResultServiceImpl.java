package team.fjut.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import team.fjut.cf.component.judge.vjudge.pojo.ProblemSolution;
import team.fjut.cf.mapper.VjJudgeResultMapper;
import team.fjut.cf.pojo.po.VjJudgeResult;
import team.fjut.cf.service.VjJudgeResultService;

/**
 * @author axiang [2020/3/21]
 */
@Service
public class VjJudgeResultServiceImpl implements VjJudgeResultService {
    @Autowired
    VjJudgeResultMapper vjJudgeResultMapper;

    @Autowired
    VirtualJudgeHttpClient virtualJudgeHttpClient;

    @Override
    public int insert(VjJudgeResult vjJudgeResult) {
        return vjJudgeResultMapper.insertSelective(vjJudgeResult);
    }

    @Override
    public VjJudgeResult select(int id) {
        VjJudgeResult vjJudgeResult = new VjJudgeResult();
        vjJudgeResult.setId(id);
        return vjJudgeResultMapper.selectOne(vjJudgeResult);
    }

    @Async
    @Override
    public void getResultFromVJ(VjJudgeResult vjJudgeResult) {
        boolean isProcessing;
        // 循环次数
        int times = 100;
        // 线程睡眠时间
        int sleepTime = 2000;
        do {
            ProblemSolution result = virtualJudgeHttpClient.getResult(vjJudgeResult.getRunId().toString());
            vjJudgeResult.setAdditionalInfo(result.getAdditionalInfo());
            vjJudgeResult.setStatus(result.getStatus());
            vjJudgeResult.setStatusType(result.getStatusType());
            vjJudgeResult.setStatusCanonical(result.getStatusCanonical());
            vjJudgeResult.setRuntime(result.getRuntime());
            vjJudgeResult.setMemory(result.getMemory());
            vjJudgeResult.setRemoteRunId(result.getRemoteRunId());
            vjJudgeResult.setProcessing(result.getProcessing() ? 1 : 0);
            vjJudgeResult.setCode(result.getCode());
            vjJudgeResult.setLength(result.getLength());
            vjJudgeResult.setLanguageCanonical(result.getLanguageCanonical());
            vjJudgeResult.setLanguage(result.getLanguage());
            vjJudgeResult.setAuthorId(result.getAuthorId());
            vjJudgeResult.setAuthor(result.getAuthor());
            vjJudgeResultMapper.updateByPrimaryKeySelective(vjJudgeResult);
            isProcessing = result.getProcessing();
            times--;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {

            }
        } while (times > 0 && isProcessing);
    }


}

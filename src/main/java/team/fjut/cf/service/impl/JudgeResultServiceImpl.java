package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.JudgeResultMapper;
import team.fjut.cf.pojo.po.JudgeResult;
import team.fjut.cf.service.JudgeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author axiang [2019/11/8]
 */
@Service
public class JudgeResultServiceImpl implements JudgeResultService {
    @Autowired
    JudgeResultMapper judgeResultMapper;

    @Override
    public JudgeResult queryJudgeResultByJudgeId(Integer judgeId) {
        return judgeResultMapper.selectByJudgeId(judgeId);
    }
}

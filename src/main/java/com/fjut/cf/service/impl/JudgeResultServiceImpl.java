package com.fjut.cf.service.impl;

import com.fjut.cf.mapper.JudgeResultMapper;
import com.fjut.cf.pojo.po.JudgeResultPO;
import com.fjut.cf.service.JudgeResultService;
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
    public JudgeResultPO queryJudgeResultByJudgeId(Integer judgeId) {
        return judgeResultMapper.selectByJudgeId(judgeId);
    }
}

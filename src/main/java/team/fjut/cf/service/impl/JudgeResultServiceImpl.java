package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.JudgeResultMapper;
import team.fjut.cf.pojo.po.JudgeResult;
import team.fjut.cf.service.JudgeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author axiang [2019/11/8]
 */
@Service
public class JudgeResultServiceImpl implements JudgeResultService {
    @Autowired
    JudgeResultMapper judgeResultMapper;

    @Override
    public List<JudgeResult> selectJudgeResult(Integer judgeId) {
        Example example = new Example(JudgeResult.class);
        example.orderBy("time").asc();
        example.createCriteria().andEqualTo("judgeId", judgeId);
        return judgeResultMapper.selectByExample(example);
    }
}

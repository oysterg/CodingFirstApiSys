package team.fjut.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.fjut.cf.mapper.VjJudgeResultMapper;
import team.fjut.cf.pojo.po.VjJudgeResult;
import team.fjut.cf.service.VjJudgeResultService;

/**
 * @author axiang [2020/3/16]
 */
@Service
public class VjJudgeResultServiceImpl implements VjJudgeResultService {

    @Autowired
    VjJudgeResultMapper vjJudgeResultMapper;


    @Override
    public int insert(VjJudgeResult vjJudgeResult) {
        return vjJudgeResultMapper.insertSelective(vjJudgeResult);
    }
}

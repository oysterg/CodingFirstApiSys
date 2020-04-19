package team.fjut.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.fjut.cf.mapper.ProblemSampleMapper;
import team.fjut.cf.pojo.po.ProblemSample;
import team.fjut.cf.service.ProblemSampleService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author axiang [2020/3/6]
 */
@Service
public class ProblemSampleServiceImpl implements ProblemSampleService {
    @Resource
    ProblemSampleMapper problemSampleMapper;

    @Override
    public List<ProblemSample> selectSamples(Integer problemId) {
        Example example = new Example(ProblemSample.class);
        example.createCriteria().andEqualTo("problemId", problemId);
        return problemSampleMapper.selectByExample(example);
    }
}

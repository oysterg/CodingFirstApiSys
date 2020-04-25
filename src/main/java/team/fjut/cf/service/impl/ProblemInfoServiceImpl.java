package team.fjut.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.fjut.cf.mapper.ProblemInfoMapper;
import team.fjut.cf.pojo.po.ProblemInfo;
import team.fjut.cf.service.ProblemInfoService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author axiang [2020/3/6]
 */
@Service
public class ProblemInfoServiceImpl implements ProblemInfoService {
    @Resource
    ProblemInfoMapper problemInfoMapper;

    @Override
    public ProblemInfo selectProblemInfo(Integer problemId) {
        Example example = new Example(ProblemInfo.class);
        example.createCriteria().andEqualTo("problemId", problemId);
        return problemInfoMapper.selectOneByExample(example);
    }

    // add by zhongml [2020/4/17]
    @Override
    public int deleteProblem(Integer problemId) {
        Example example = new Example(ProblemInfo.class);
        example.createCriteria().andEqualTo("problemId", problemId);
        return problemInfoMapper.deleteByExample(example);
    }

    // add by zhongml [2020/4/17]
    @Override
    public List<ProblemInfo> selectAll() {
        return problemInfoMapper.selectAll();
    }


}

package team.fjut.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.fjut.cf.mapper.ProblemViewMapper;
import team.fjut.cf.pojo.po.ProblemView;
import team.fjut.cf.service.ProblemViewService;
import tk.mybatis.mapper.entity.Example;

/**
 * @author axiang [2020/3/6]
 */
@Service
public class ProblemViewServiceImpl implements ProblemViewService {
    @Autowired
    ProblemViewMapper problemViewMapper;

    @Override
    public ProblemView selectView(Integer problemId) {
        Example example = new Example(ProblemView.class);
        example.createCriteria().andEqualTo("problemId", problemId);
        return problemViewMapper.selectOneByExample(example);
    }
}

package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.ProblemTagMapper;
import team.fjut.cf.mapper.ProblemTagRecordMapper;
import team.fjut.cf.pojo.po.ProblemTagPO;
import team.fjut.cf.pojo.po.ProblemTagRecordPO;
import team.fjut.cf.service.ProblemTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author axiang [2019/10/30]
 */
@Service
public class ProblemTagServiceImpl implements ProblemTagService {
    @Resource
    ProblemTagMapper problemTagMapper;

    @Resource
    ProblemTagRecordMapper problemTagRecordMapper;

    @Override
    public List<ProblemTagPO> select() {
        return problemTagMapper.all();
    }

    @Override
    public List<ProblemTagRecordPO> selectTag(Integer problemId) {
        Example example = new Example(ProblemTagRecordPO.class);
        example.createCriteria().andEqualTo("problemId", problemId);
        return problemTagRecordMapper.selectByExample(example);
    }

}

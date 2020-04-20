package team.fjut.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.fjut.cf.mapper.ProblemStarMapper;
import team.fjut.cf.pojo.po.ProblemStarPO;
import team.fjut.cf.service.ProblemStarService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author zhongml [2020/4/17]
 */
@Service
public class ProblemStarServiceImpl implements ProblemStarService {

    @Resource
    ProblemStarMapper problemStarMapper;

    @Override
    public List<ProblemStarPO> selectStar(Integer problemId) {
        Example example = new Example(ProblemStarPO.class);
        example.createCriteria().andEqualTo("problemId", problemId);
        return problemStarMapper.selectByExample(example);
    }
}

package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.ProblemTagMapper;
import team.fjut.cf.pojo.po.ProblemTagPO;
import team.fjut.cf.service.ProblemTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang [2019/10/30]
 */
@Service
public class ProblemTagServiceImpl implements ProblemTagService {
    @Autowired
    ProblemTagMapper problemTagMapper;

    @Override
    public List<ProblemTagPO> select() {
        return problemTagMapper.select();
    }
}

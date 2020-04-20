package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import team.fjut.cf.mapper.ProblemTagMapper;
import team.fjut.cf.mapper.ProblemTagRecordMapper;
import team.fjut.cf.pojo.po.ProblemTagPO;
import team.fjut.cf.pojo.po.ProblemTagRecordPO;
import team.fjut.cf.service.ProblemTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    // add by zhongml [2020/4/20]
    @Override
    public List<ProblemTagPO> selectByCondition(Integer pageNum, Integer pageSize, String sort, String name) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(ProblemTagPO.class);

        if(sort != null && sort.equals("descending")) {
            example.orderBy("id").desc();
        }
        else {
            example.orderBy("id").asc();
        }
        Example.Criteria criteria = example.createCriteria();

        if (Objects.nonNull(name)) {
            criteria.andLike("name", name);
        }

        List<ProblemTagPO> result = problemTagMapper.selectByExample(example);
        return result;
    }

    // add by zhongml [2020/4/20]
    @Override
    public int countByCondition(String name) {
        Example example = new Example(ProblemTagPO.class);
        Example.Criteria criteria = example.createCriteria();
        // 非空则查询条件
        if (Objects.nonNull(name)) {
            criteria.andLike("name", name);
        }
        return problemTagMapper.selectCountByExample(example);
    }

    // add by zhongml [2020/4/20]
    public int createTag(ProblemTagPO problemTag) {
        return problemTagMapper.insertSelective(problemTag);
    }

    // add by zhongml [2020/4/20]
    public int updateTag(ProblemTagPO problemTag) {
        Example example = new Example(ProblemTagPO.class);
        example.createCriteria().andEqualTo("id", problemTag.getId());
        return problemTagMapper.updateByExampleSelective(problemTag, example);
    }

    // add by zhongml [2020/4/20]
    public int deleteTag(Integer id) {
        Example example = new Example(ProblemTagPO.class);
        example.createCriteria().andEqualTo("id", id);
        return problemTagMapper.deleteByExample(example);
    }

    // add by zhongml [2020/4/17]
    @Override
    public List<ProblemTagRecordPO> selectTagRecord(Integer problemId) {
        Example example = new Example(ProblemTagRecordPO.class);
        example.createCriteria().andEqualTo("problemId", problemId);
        return problemTagRecordMapper.selectByExample(example);
    }

}

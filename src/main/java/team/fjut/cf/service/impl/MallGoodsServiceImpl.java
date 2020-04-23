package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import team.fjut.cf.mapper.MallGoodsMapper;
import team.fjut.cf.pojo.po.MallGoods;
import team.fjut.cf.service.MallGoodsService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author axiang [2019/11/12]
 */
@Service
public class MallGoodsServiceImpl implements MallGoodsService {
    @Resource
    MallGoodsMapper mallGoodsMapper;

    @Override
    public List<MallGoods> pages(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MallGoods> mallGoods = mallGoodsMapper.all();
        return mallGoods;
    }

    @Override
    public Integer selectAllCount() {
        return mallGoodsMapper.allCount();
    }

    @Override
    public MallGoods selectByGoodsId(Integer id) {
        return mallGoodsMapper.selectByGoodsId(id);
    }

    // add by zhongml [2020/4/21]
    @Override
    public List<MallGoods> selectByCondition(Integer pageNum, Integer pageSize, String sort, Integer id, String name) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(MallGoods.class);

        if(sort != null && sort.equals("descending")) {
            example.orderBy("id").desc();
        }
        else {
            example.orderBy("id").asc();
        }
        Example.Criteria criteria = example.createCriteria();

        if (Objects.nonNull(id)) {
            criteria.andEqualTo("id", id);
        }
        if (Objects.nonNull(name)) {
            criteria.andLike("name", name);
        }

        List<MallGoods> result = mallGoodsMapper.selectByExample(example);
        return result;
    }

    // add by zhongml [2020/4/21]
    @Override
    public int countByCondition(Integer id, String name) {
        Example example = new Example(MallGoods.class);
        Example.Criteria criteria = example.createCriteria();
        // 非空则查询条件
        if (Objects.nonNull(id)) {
            criteria.andEqualTo("id", id);
        }
        if (Objects.nonNull(name)) {
            criteria.andLike("name", name);
        }
        return mallGoodsMapper.selectCountByExample(example);
    }

    // add by zhongml [2020/4/21]
    @Override
    public int createGoods(MallGoods mallGoods) {
        return mallGoodsMapper.insertSelective(mallGoods);
    }

    // add by zhongml [2020/4/21]
    @Override
    public int updateGoods(MallGoods mallGoods) {
        Example example = new Example(MallGoods.class);
        example.createCriteria().andEqualTo("id", mallGoods.getId());
        return mallGoodsMapper.updateByExampleSelective(mallGoods, example);
    }

    // add by zhongml [2020/4/21]
    @Override
    public int deleteGoods(Integer id) {
        Example example = new Example(MallGoods.class);
        example.createCriteria().andEqualTo("id", id);
        return mallGoodsMapper.deleteByExample(example);
    }
}

package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import team.fjut.cf.mapper.MallGoodsMapper;
import team.fjut.cf.pojo.po.MallGoodsPO;
import team.fjut.cf.service.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang [2019/11/12]
 */
@Service
public class MallGoodsServiceImpl implements MallGoodsService {
    @Autowired
    MallGoodsMapper mallGoodsMapper;

    @Override
    public List<MallGoodsPO> pages(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MallGoodsPO> mallGoodsPOS = mallGoodsMapper.selectAll();
        return mallGoodsPOS;
    }

    @Override
    public Integer selectAllCount() {
        return mallGoodsMapper.selectAllCount();
    }

    @Override
    public MallGoodsPO selectByGoodsId(Integer id) {
        return mallGoodsMapper.selectByGoodsId(id);
    }
}

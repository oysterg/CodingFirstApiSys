package com.fjut.cf.service.impl;

import com.fjut.cf.mapper.MallGoodsMapper;
import com.fjut.cf.pojo.po.MallGoodsPO;
import com.fjut.cf.service.MallGoodsService;
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
    public List<MallGoodsPO> pages(Integer startIndex, Integer pageSize) {
        return mallGoodsMapper.pages(startIndex, pageSize);
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

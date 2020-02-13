package com.fjut.cf.service.impl;

import com.fjut.cf.mapper.ProblemTagMapper;
import com.fjut.cf.pojo.po.ProblemTagPO;
import com.fjut.cf.service.ProblemTagService;
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

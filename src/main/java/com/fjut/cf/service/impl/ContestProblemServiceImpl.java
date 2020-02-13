package com.fjut.cf.service.impl;

import com.fjut.cf.mapper.ContestProblemMapper;
import com.fjut.cf.pojo.po.ContestProblemPO;
import com.fjut.cf.service.ContestProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang [2019/11/21]
 */
@Service
public class ContestProblemServiceImpl implements ContestProblemService {
    @Autowired
    ContestProblemMapper contestProblemMapper;

    @Override
    public List<ContestProblemPO> selectByContestId(Integer contestId) {
        return contestProblemMapper.selectByContestId(contestId);
    }
}

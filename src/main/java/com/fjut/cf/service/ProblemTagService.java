package com.fjut.cf.service;

import com.fjut.cf.pojo.po.ProblemTagPO;

import java.util.List;

/**
 * @author axiang [2019/10/30]
 */
public interface ProblemTagService {
    /**
     * 查询全部题目标签
     * @return
     */
    List<ProblemTagPO> select();



}

package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.ProblemTagPO;

import java.util.List;

/**
 * @author axiang [2019/10/30]
 */
public interface ProblemTagMapper {
    /**
     * 查找全部题目标签类型
     * @return
     */
    List<ProblemTagPO> select();
}

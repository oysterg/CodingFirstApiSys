package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.ProblemInfoWithDifficultPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目模块下的多表查询
 *
 * @author axiang [2019/10/22]
 */
public interface ProblemMapper {
    /**
     * 多条件查询题目基本信息，带难度
     *
     * @param pageSize
     * @param startIndex
     * @param title
     * @param tagId
     * @return
     */
    List<ProblemInfoWithDifficultPO> pagesFullProblemInfo(@Param("title") String title,
                                                          @Param("tagId") Integer tagId,
                                                          @Param("startIndex") Integer startIndex,
                                                          @Param("pageSize") Integer pageSize);


}

package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.ProblemInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/10/22]
 */
public interface ProblemInfoMapper {

    /**
     * 插入一条题目基本信息记录
     *
     * @param problemInfoPO
     * @return
     */
    Integer insert(@Param("problemInfoPO") ProblemInfoPO problemInfoPO);

    /**
     * 根据 problemId 删除 题目基本信息
     *
     * @param problemId
     * @return
     */
    Integer deleteByProblemId(@Param("problemId") Integer problemId);

    /**
     * 更新题目提交总数+1
     *
     * @param problemId
     * @return
     */
    Integer updateTotalSubmitAddOne(@Param("problemId") Integer problemId);

    /**
     * 更新题目提交AC总数+1
     *
     * @param problemId
     * @return
     */
    Integer updateTotalAcAddOne(@Param("problemId") Integer problemId);

    /**
     * 更新题目提交用户总数+1
     *
     * @param problemId
     * @return
     */
    Integer updateTotalSubmitUserAddOne(@Param("problemId") Integer problemId);

    /**
     * 更新提交提交用户的AC总数+1
     *
     * @param problemId
     * @return
     */
    Integer updateTotalAcUserAddOne(@Param("problemId") Integer problemId);

    /**
     * 带条件查询题目总数
     *
     * @param title
     * @param tagId
     * @return
     */
    Integer selectCountByConditions(@Param("title") String title, @Param("tagId") Integer tagId);

    /**
     * 查找 pageSize 页的题目基本信息
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<ProblemInfoPO> pages(@Param("startIndex") Integer startIndex,
                              @Param("pageSize") Integer pageSize);

    /**
     * 根据题目ID查询题目基本信息
     *
     * @param problemId
     * @return
     */
    ProblemInfoPO queryByProblemId(@Param("problemId") Integer problemId);

    /**
     * 查询用户未解决的题目列表
     * @param username
     * @return
     */
    List<ProblemInfoPO> queryUnSolvedProblemsByUsername(@Param("username") String username);

}

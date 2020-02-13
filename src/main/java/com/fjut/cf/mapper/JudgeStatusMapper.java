package com.fjut.cf.mapper;

import com.fjut.cf.pojo.po.JudgeStatusPO;
import com.fjut.cf.pojo.vo.StatusCountVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/10/30]
 */
public interface JudgeStatusMapper {

    /**
     * 插入一条评测记录
     *
     * @param judgeStatusPO
     * @return
     */
    Integer insert(@Param("judgeStatusPO") JudgeStatusPO judgeStatusPO);

    /**
     * 根据ID删除一条评测记录
     *
     * @param id
     * @return
     */
    Integer deleteById(@Param("id") Integer id);

    /**
     * 根据评测ID更新评测记录中的评测结果、得分、时间消耗和内存消耗
     *
     * @param judgeStatusPO
     * @return
     */
    Integer updateAfterJudgedById(@Param("judgeStatusPO") JudgeStatusPO judgeStatusPO);

    /**
     * 根据评测ID更新评测记录的评测结果
     *
     * @param id
     * @param result
     * @return
     */
    Integer updateResultById(@Param("id") Integer id, @Param("result") Integer result);

    /**
     * 查询最近 days 天的提交统计
     *
     * @param days
     * @return
     */
    List<StatusCountVO> selectCountByDay(@Param("days") int days);

    /**
     * 查询最大的ID
     *
     * @return
     */
    Integer queryMaxId();

    /**
     * 根据用户名和题目ID查询提交总数
     *
     * @param username
     * @param problemId
     * @return
     */
    Integer selectCountByUsernameAndProblemId(@Param("username") String username,
                                              @Param("problemId") Integer problemId);

    /**
     * 根据用户名和题目ID查询提交总数中的AC次数
     *
     * @param username
     * @param problemId
     * @return
     */
    Integer selectCountIfAcByUsernameAndProblemId(@Param("username") String username,
                                                  @Param("problemId") Integer problemId);

    /**
     * 根据用户名查询评测记录条数
     *
     * @param username
     * @return
     */
    Integer selectCountByUsername(@Param("username") String username);
}

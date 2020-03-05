package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.ProblemInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author axiang [2019/10/22]
 */
public interface ProblemInfoMapper extends Mapper<ProblemInfo> {

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
     * 根据题目ID查询题目基本信息
     *
     * @param problemId
     * @return
     */
    ProblemInfo selectByProblemId(@Param("problemId") Integer problemId);

    /**
     * 查询用户未解决的题目列表
     * @param username
     * @return
     */
    List<ProblemInfo> selectUnSolvedProblemsByUsername(@Param("username") String username);

}

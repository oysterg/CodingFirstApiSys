package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.ProblemTypeCountPO;
import team.fjut.cf.pojo.po.UserProblemSolved;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author axiang [2019/11/14]
 */
public interface UserProblemSolvedMapper extends Mapper<UserProblemSolved> {
    /**
     * 根据用户名和题目ID更新尝试次数+1
     *
     * @param username
     * @param problemId
     * @return
     */
    Integer updateTryCountAddOne(@Param("username") String username, @Param("problemId") Integer problemId);

    /**
     * 根据用户名和题目ID更新AC次数+1
     *
     * @param username
     * @param problemId
     * @return
     */
    Integer updateSolvedCountAddOne(@Param("username") String username, @Param("problemId") Integer problemId);

    /**
     * 根据用户名和题目ID更新第一次解决的时间
     *
     * @param username
     * @param problemId
     * @return
     */
    Integer updateFirstSolvedTime(@Param("username") String username, @Param("problemId") Integer problemId);

    /**
     * 根据用户名和题目ID更新最后尝试时间
     *
     * @param username
     * @param problemId
     * @return
     */
    Integer updateLastTryTime(@Param("username") String username, @Param("problemId") Integer problemId);

    /**
     * 根据用户名查找对应的用户解答记录
     *
     * @param username
     * @return
     */
    List<UserProblemSolved> selectByUsername(@Param("username") String username);

    /**
     * 根据用户名和题目ID查找对应记录
     *
     * @param username
     * @param problemId
     * @return
     */
    UserProblemSolved selectByUsernameAndProblemId(@Param("username") String username, @Param("problemId") Integer problemId);

    /**
     * 根据用户名和题目ID查找对应记录数量
     *
     * @param username
     * @param problemId
     * @return
     */
    Integer selectCountByUsernameAndProblemId(@Param("username") String username, @Param("problemId") Integer problemId);


    /**
     * 查询用户完成题目中的题目类型总计
     *
     * @param username
     * @return
     */
    List<ProblemTypeCountPO> selectCountTypeByUsername(@Param("username") String username);
}

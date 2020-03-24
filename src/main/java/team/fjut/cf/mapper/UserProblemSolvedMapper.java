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
     * 根据用户名查找对应的用户解答记录
     *
     * @param username
     * @return
     */
    List<UserProblemSolved> selectByUsername(@Param("username") String username);


    /**
     * 查询用户完成题目中的题目类型总计
     *
     * @param username
     * @return
     */
    List<ProblemTypeCountPO> selectCountTypeByUsername(@Param("username") String username);
}

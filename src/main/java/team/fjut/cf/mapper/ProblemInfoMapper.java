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
     * 查询用户未解决的题目列表
     * @param username
     * @return
     */
    List<ProblemInfo> selectUnSolvedProblemsByUsername(@Param("username") String username);

}

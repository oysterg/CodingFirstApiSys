package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.ProblemViewPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [2019/11/7]
 */
public interface ProblemViewMapper {
    /**
     * 根据题目ID查询题目样例内容
     *
     * @param problemId
     * @return
     */
    ProblemViewPO selectByProblemId(@Param("problemId") Integer problemId);
}

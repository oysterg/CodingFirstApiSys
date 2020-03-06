package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.ProblemView;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author axiang [2019/11/7]
 */
public interface ProblemViewMapper extends Mapper<ProblemView> {
    /**
     * 根据题目ID查询题目样例内容
     *
     * @param problemId
     * @return
     */
    ProblemView selectByProblemId(@Param("problemId") Integer problemId);
}

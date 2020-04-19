package team.fjut.cf.service;

import team.fjut.cf.pojo.po.ProblemSample;
import team.fjut.cf.pojo.po.ProblemView;

/**
 * @author axiang [2020/3/6]
 */
public interface ProblemViewService {
    /**
     * 查询题目详情
     *
     * @param problemId
     * @return
     */
    ProblemView selectView(Integer problemId);

    /**
     * 修改题目详情
     *
     * @param problemView
     * @return
     */
    int updateProblem(ProblemView problemView);
}

package team.fjut.cf.service;

import team.fjut.cf.pojo.po.ProblemTagPO;
import team.fjut.cf.pojo.po.ProblemTagRecordPO;

import java.util.List;

/**
 * @author axiang [2019/10/30]
 */
public interface ProblemTagService {
    /**
     * 查询全部题目标签
     * @return
     */
    List<ProblemTagPO> select();

    /**
     * 根据题目ID查询标签
     * @param problemId
     * @return
     */
    List<ProblemTagRecordPO> selectTag(Integer problemId);

}

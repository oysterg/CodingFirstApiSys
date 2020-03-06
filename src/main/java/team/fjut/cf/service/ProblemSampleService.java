package team.fjut.cf.service;

import team.fjut.cf.pojo.po.ProblemSample;

import java.util.List;

/**
 * @author axiang
 */
public interface ProblemSampleService {
    /**
     * 根据题目ID查找样例集
     *
     * @param problemId
     * @return
     */
    List<ProblemSample> selectSamples(Integer problemId);
}

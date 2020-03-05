package team.fjut.cf.service;

import team.fjut.cf.pojo.vo.StatusListVO;

import java.util.List;

public interface ViewJudgeStatusService {
    /**
     * 分页查询评测列表
     *
     * @param pageNum
     * @param pageSize
     * @param contestId
     * @param nickname
     * @param problemId
     * @param result
     * @param language
     * @return
     */
    List<StatusListVO> pagesByConditions(Integer pageNum, Integer pageSize,
                                         Integer contestId,
                                         String nickname, Integer problemId, Integer result, Integer language);

    /**
     * 查询页数
     *
     * @param contestId
     * @param nickname
     * @param problemId
     * @param result
     * @param language
     * @return
     */
    int countByConditions(Integer contestId,
                          String nickname, Integer problemId, Integer result, Integer language);

}

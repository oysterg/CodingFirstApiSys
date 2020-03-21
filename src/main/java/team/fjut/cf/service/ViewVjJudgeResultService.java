package team.fjut.cf.service;

import team.fjut.cf.pojo.vo.VjJudgeResultVO;

import java.util.List;

/**
 * @author axiang
 */
public interface ViewVjJudgeResultService {

    /**
     * 按条件对评测结果进行分页
     *
     * @param pageNum
     * @param pageSize
     * @param nickname
     * @param problemId
     * @param status
     * @param language
     * @return
     */
    List<VjJudgeResultVO> pagesByConditions(int pageNum, int pageSize,
                                            String nickname,
                                            String problemId,
                                            Integer status,
                                            String language);

    /**
     * 按条件计算条数
     *
     * @param nickname
     * @param problemId
     * @param status
     * @param language
     * @return
     */
    int countByConditions(String nickname,
                          String problemId,
                          Integer status,
                          String language);
}

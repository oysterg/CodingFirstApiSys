package team.fjut.cf.service;

import team.fjut.cf.pojo.vo.ProblemListAdminVO;
import team.fjut.cf.pojo.vo.ProblemListVO;

import java.util.List;

/**
 * 题目信息视图 Service
 *
 * @author axiang [2020/3/5]
 */
public interface ViewProblemInfoService {
    /**
     * 根据条件查询
     *
     * @param pageNum
     * @param pageSize
     * @param problemId
     * @param title
     * @param tagId
     * @param username
     * @return
     */
    List<ProblemListVO> pagesByConditions(int pageNum, int pageSize,
                                          Integer problemId, String title, Integer tagId,
                                          String username);

    /**
     * 根据条件查询数量
     *
     * @param problemId
     * @param title
     * @param tagId
     * @return
     */
    int countByConditions(Integer problemId, String title, Integer tagId);

    /**
     * 后台根据条件查询
     *
     * @param pageNum
     * @param pageSize
     * @param title
     * @param difficultLevel
     * @return
     */
    List<ProblemListAdminVO> selectByPage(int pageNum, int pageSize, String title, Integer difficultLevel);


    /**
     * 后台根据条件查询数量
     *
     * @param title
     * @param difficultLevel
     * @return
     */
    int countByPage(String title, Integer difficultLevel);
}

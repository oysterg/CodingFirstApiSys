package team.fjut.cf.service;

import team.fjut.cf.pojo.po.ProblemInfoPO;
import team.fjut.cf.pojo.po.ProblemSamplePO;
import team.fjut.cf.pojo.po.ProblemViewPO;
import team.fjut.cf.pojo.vo.ProblemListVO;
import team.fjut.cf.pojo.vo.UserRadarVO;

import java.util.List;

/**
 * @author axiang [2019/10/22]
 */
public interface ProblemService {
    /**
     * 分页带条件查询题目展示详情
     *
     * @param username
     * @param title
     * @param tagId
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<ProblemListVO> pagesByConditions(String username, String title, Integer tagId, Integer startIndex, Integer pageSize);

    /**
     * 带条件查询题目数量
     *
     * @param title
     * @param tagId
     * @return
     */
    Integer selectCountByConditions(String title, Integer tagId);

    /**
     * 根据题目ID查询题目基本信息
     *
     * @param problemId
     * @return
     */
    ProblemInfoPO selectProblemInfoByProblemId(Integer problemId);

    /**
     * 根据题目ID查询题目视图信息内容
     *
     * @param problemId
     * @return
     */
    ProblemViewPO selectProblemViewByProblemId(Integer problemId);

    /**
     * 根据题目ID查询题目输入输出样例
     *
     * @param problemId
     * @return
     */
    List<ProblemSamplePO> selectProblemSampleByProblemId(Integer problemId);

    /**
     * 查询用户题目雷达图
     *
     * @param username
     * @return
     */
    List<UserRadarVO> selectUserProblemRadarByUsername(String username);

    /**
     * 查询推荐题目列表
     *
     * @param username
     * @return
     */
    List<ProblemInfoPO> selectRecommendProblemsByUsername(String username);

}

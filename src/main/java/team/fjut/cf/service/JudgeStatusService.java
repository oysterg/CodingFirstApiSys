package team.fjut.cf.service;

import team.fjut.cf.pojo.po.JudgeStatusPO;
import team.fjut.cf.pojo.vo.JudgeStatusVO;
import team.fjut.cf.pojo.vo.StatusCountVO;

import java.util.List;

/**
 * @author axiang [2019/10/30]
 */
public interface JudgeStatusService {
    /**
     * 插入一条评测记录
     *
     * @param judgeStatusPO
     * @return
     */
    Integer insert(JudgeStatusPO judgeStatusPO);

    /**
     * 如果提交代码成功更新数据库内容
     * 更新题目表
     * 更新用户解答记录表
     *
     * @param judgeStatusPO
     * @return
     */
    Boolean updateIfSubmitSuccess(JudgeStatusPO judgeStatusPO);

    /**
     * 如果提交代码失败更新数据库内容
     *
     * @param judgeId
     * @return
     */
    Boolean updateIfSubmitFail(Integer judgeId);

    /**
     * 查询本地评测机中的结果，异步做法
     *
     * @param judgeStatusPO
     * @throws Exception
     */
    void selectFromLocalJudgeAsync(JudgeStatusPO judgeStatusPO) throws Exception;

    /**
     * 查询最近 days 天的提交统计
     *
     * @param days
     * @return
     */
    List<StatusCountVO> selectCountByDay(int days);


    /**
     * 分页查询评测列表
     *
     * @param pageNum
     * @param pageSize
     * @param contestId
     * @param nick
     * @param problemId
     * @param result
     * @param language
     * @return
     */
    List<JudgeStatusVO> pagesByConditions(Integer pageNum, Integer pageSize, Integer contestId, String nick, Integer problemId, Integer result, Integer language);

    /**
     * 查询评测列表视图的大小
     *
     * @param contestId
     * @param nick
     * @param problemId
     * @param result
     * @param language
     * @return
     */
    Integer selectCountByConditions(Integer contestId, String nick, Integer problemId, Integer result, Integer language);

    /**
     * 根据评测ID查询评测列表视图
     *
     * @param id
     * @return
     */
    JudgeStatusVO selectAsViewJudgeStatusById(Integer id);

    /**
     * 根据用户名查询评测记录条数
     *
     * @param username
     * @return
     */
    Integer selectCountByUsername(String username);

}

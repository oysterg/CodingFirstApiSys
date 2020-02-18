package team.fjut.cf.service;

import team.fjut.cf.pojo.po.ChallengeBlockConditionPO;
import team.fjut.cf.pojo.po.ChallengeUserOpenBlockPO;
import team.fjut.cf.pojo.vo.ChallengeBlockConditionVO;
import team.fjut.cf.pojo.vo.ChallengeBlockVO;
import team.fjut.cf.pojo.vo.UserChallengeBlockVO;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
public interface ChallengeBlockService {

    /**
     * 插入一条解锁记录并发送解锁消息
     *
     * @param challengeUserOpenBlockPO
     * @return
     */
    Integer unlockBlock(ChallengeUserOpenBlockPO challengeUserOpenBlockPO);

    /**
     * 根据用户名查询挑战模式模块
     *
     * @param username
     * @return
     */
    List<UserChallengeBlockVO> selectByUsername(String username);

    /**
     * 根据模块ID查询模块详情
     *
     * @param blockId
     * @param username
     * @return
     */
    ChallengeBlockVO selectByBlockIdAndUsername(Integer blockId, String username);

    /**
     * 查询模块之间的关系
     *
     * @return
     */
    List<ChallengeBlockConditionPO> selectConditions();

    /**
     * 查询模块解锁条件
     *
     * @param blockId
     * @return
     */
    List<ChallengeBlockConditionVO> selectConditionByBlockId(Integer blockId);


    /**
     * 更新挑战模块开放模块
     * 逻辑如下：
     * 已知使用的前置条件：一道题目完成了判题，返回了AC结果
     * 先获取该题隶属挑战模块，如果不属于任何模块则直接退出，如果属于进入下一步骤
     * 获取题目隶属挑战模块的后置模块（所有可能会解锁的待解锁模块）
     * 依次对这些可能会解锁的待解锁模块计算解锁条件，条件全部满足则解锁
     *
     * @param username
     * @param problemId
     * @return
     */
    void updateOpenBlock(String username,Integer problemId);
}

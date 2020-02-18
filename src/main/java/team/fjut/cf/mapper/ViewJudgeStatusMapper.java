package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.ViewJudgeStatusPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/10/31]
 */
public interface ViewJudgeStatusMapper {

    /**
     * 分页查询满足条件的评测视图
     *
     * @param startIndex
     * @param pageSize
     * @param contestId
     * @param nick
     * @param problemId
     * @param result
     * @param language
     * @return
     */
    List<ViewJudgeStatusPO> pagesByConditions(
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize,
            @Param("contestId") Integer contestId,
            @Param("nick") String nick,
            @Param("problemId") Integer problemId,
            @Param("result") Integer result,
            @Param("language") Integer language);

    /**
     * 根据条件查询视图的内容大小
     *
     * @param contestId
     * @param nick
     * @param problemId
     * @param result
     * @param language
     * @return
     */
    Integer selectCountByConditions(
            @Param("contestId") Integer contestId,
            @Param("nick") String nick,
            @Param("problemId") Integer problemId,
            @Param("result") Integer result,
            @Param("language") Integer language);

    /**
     * 根据评测ID查询评测记录
     *
     * @param id
     * @return
     */
    ViewJudgeStatusPO queryById(@Param("id") Integer id);
}

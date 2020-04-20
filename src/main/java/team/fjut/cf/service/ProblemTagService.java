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
      * @author zhongml [2020/4/17]
      * 查询分页题目标签
      *
      * @param pageNum
      * @param pageSize
      * @param sort
      * @param name
      * @return
      */
     List<ProblemTagPO> selectByCondition(Integer pageNum, Integer pageSize, String sort, String name);

    /**
     * @author zhongml [2020/4/20]
     * 添加一条题目标签
     *      *
     * @param problemTag
     * @return
     */
    int createTag(ProblemTagPO problemTag);

    /**
     * @author zhongml [2020/4/20]
     * 根据ID修改题目标签
     *      *
     * @param problemTag
     * @return
     */
    int updateTag(ProblemTagPO problemTag);

    /**
     * @author zhongml [2020/4/20]
     * 删除一条题目标签
     *      *
     * @param id
     * @return
     */
    int deleteTag(Integer id);

    /**
     * @author zhongml [2020/4/20]
     * 查询题目标签数量
     *
     * @param name
     * @return
     */
     int countByCondition(String name);

    /**
     * @author zhongml [2020/4/17]
     * 根据题目ID查询用户标签记录
     *
     * @param problemId
     * @return
     */
    List<ProblemTagRecordPO> selectTagRecord(Integer problemId);

}

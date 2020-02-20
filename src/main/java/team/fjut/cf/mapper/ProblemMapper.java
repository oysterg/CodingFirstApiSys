package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.ProblemInfoWithDifficultPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目模块下的多表查询
 *
 * @author axiang [2019/10/22]
 */
public interface ProblemMapper {
    /**
     * 多条件查询题目基本信息，带难度
     *
     * @param title
     * @param tagId
     * @return
     */
    List<ProblemInfoWithDifficultPO> selectFullProblemInfoByConditions(@Param("title") String title,
                                                                       @Param("tagId") Integer tagId
    );


}

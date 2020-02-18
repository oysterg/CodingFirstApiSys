package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.BugReportPO;
import org.apache.ibatis.annotations.Param;

/**
 * @author axiang [2019/10/18]
 */
public interface BugReportedMapper {
    /**
     * 插入一条bug反馈记录
     *
     * @param bugReportPO
     * @return
     */
    Integer insert(@Param("bugReportPO") BugReportPO bugReportPO);
}


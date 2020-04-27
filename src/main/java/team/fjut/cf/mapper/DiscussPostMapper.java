package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.DiscussPostPO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author axiang
 */
public interface DiscussPostMapper extends Mapper<DiscussPostPO> {
    /**
     * 查找全部的聊天帖子
     *
     * @return
     */
    List<DiscussPostPO> all();

    /**
     * 查询全部聊天帖子的数量
     *
     * @return
     */
    Integer allCount();
}

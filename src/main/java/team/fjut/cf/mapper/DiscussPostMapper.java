package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.DiscussPostPO;

import java.util.List;

/**
 * @author axiang
 */
public interface DiscussPostMapper {
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

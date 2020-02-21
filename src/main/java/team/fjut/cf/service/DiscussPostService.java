package team.fjut.cf.service;

import team.fjut.cf.pojo.po.DiscussPostPO;

import java.util.List;

/**
 * @author axiang
 */
public interface DiscussPostService {
    /**
     * 分页查找聊天帖子
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<DiscussPostPO> pages(int pageNum, int pageSize);

    /**
     * 查询全部聊天帖子数量
     *
     * @return
     */
    Integer allCount();
}

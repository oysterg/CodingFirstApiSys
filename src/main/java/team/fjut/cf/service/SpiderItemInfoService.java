package team.fjut.cf.service;

import team.fjut.cf.pojo.po.SpiderItemInfo;

import java.util.List;

/**
 * @author axiang [2020/4/29]
 */
public interface SpiderItemInfoService {
    List<SpiderItemInfo> pages(int pageNum, int pageSize);
}

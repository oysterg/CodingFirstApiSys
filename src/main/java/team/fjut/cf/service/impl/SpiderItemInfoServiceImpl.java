package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import team.fjut.cf.mapper.SpiderItemInfoMapper;
import team.fjut.cf.pojo.po.SpiderItemInfo;
import team.fjut.cf.service.SpiderItemInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author axiang [2020/4/29]
 */
@Service
public class SpiderItemInfoServiceImpl implements SpiderItemInfoService {
    @Resource
    SpiderItemInfoMapper spiderItemInfoMapper;

    @Override
    public List<SpiderItemInfo> pages(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return spiderItemInfoMapper.selectAll();
    }
}

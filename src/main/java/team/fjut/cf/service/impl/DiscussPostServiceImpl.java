package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.fjut.cf.mapper.DiscussPostMapper;
import team.fjut.cf.pojo.po.DiscussPostPO;
import team.fjut.cf.service.DiscussPostService;

import java.util.List;

/**
 * @author axiang [2020/2/21]
 */
@Service
public class DiscussPostServiceImpl implements DiscussPostService {
    @Autowired
    DiscussPostMapper discussPostMapper;

    @Override
    public List<DiscussPostPO> pages(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DiscussPostPO> discussPostPOS = discussPostMapper.all();
        return discussPostPOS;
    }

    @Override
    public Integer allCount() {
        return discussPostMapper.allCount();
    }
}

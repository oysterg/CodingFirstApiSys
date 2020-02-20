package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import team.fjut.cf.mapper.UserMessageMapper;
import team.fjut.cf.pojo.po.UserMessagePO;
import team.fjut.cf.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {
    @Autowired
    UserMessageMapper userMessageMapper;

    @Override
    public Integer insert(UserMessagePO userMessagePO) {
        return userMessageMapper.insert(userMessagePO);
    }

    @Override
    public Integer updateSetReadByUsernameAndId(String username, Integer id) {
        return userMessageMapper.updateSetReadByUsernameAndId(username, id);
    }

    @Override
    public Integer updateSetReadByUsername(String username) {
        return userMessageMapper.updateSetAllReadByUsername(username);
    }

    @Override
    public List<UserMessagePO> pagesByUsername(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserMessagePO> userMessagePOS = userMessageMapper.selectByUsername(username);
        return userMessagePOS;
    }

    @Override
    public Integer selectCountUserMessageByUsername(String username) {
        return userMessageMapper.selectCountUserMessageByUsername(username);
    }

    @Override
    public List<UserMessagePO> pagesUnreadByUsername(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserMessagePO> userMessagePOS = userMessageMapper.selectUnreadByUsername(username);
        return userMessagePOS;
    }

    @Override
    public Integer selectCountUnreadByUsername(String username) {
        return userMessageMapper.selectCountUnreadByUsername(username);
    }
}

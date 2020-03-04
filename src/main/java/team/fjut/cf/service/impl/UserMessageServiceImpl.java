package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import team.fjut.cf.mapper.UserMessageMapper;
import team.fjut.cf.pojo.po.UserMessage;
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
    public Integer insert(UserMessage userMessage) {
        return userMessageMapper.insert(userMessage);
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
    public List<UserMessage> pagesByUsername(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserMessage> userMessages = userMessageMapper.selectByUsername(username);
        return userMessages;
    }

    @Override
    public Integer selectCountUserMessageByUsername(String username) {
        return userMessageMapper.selectCountUserMessageByUsername(username);
    }

    @Override
    public List<UserMessage> pagesUnreadByUsername(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserMessage> userMessages = userMessageMapper.selectUnreadByUsername(username);
        return userMessages;
    }

    @Override
    public Integer selectCountUnreadByUsername(String username) {
        return userMessageMapper.selectCountUnreadByUsername(username);
    }
}

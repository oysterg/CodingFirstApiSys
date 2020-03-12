package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.fjut.cf.mapper.UserMessageMapper;
import team.fjut.cf.pojo.enums.MessageStatusType;
import team.fjut.cf.pojo.po.UserMessage;
import team.fjut.cf.pojo.vo.UserMessageListVO;
import team.fjut.cf.service.UserMessageService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author axiang [2019/11/11]
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {
    @Autowired
    UserMessageMapper userMessageMapper;

    @Override
    public List<UserMessageListVO> pagesByConditions(int pageNum, int pageSize,
                                                     String toUsername,
                                                     String fromUsername, Integer status, String title) {
        List<UserMessageListVO> results = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UserMessage.class);
        example.orderBy("time").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("toUsername", toUsername);
        if (Objects.nonNull(fromUsername)) {
            criteria.andEqualTo("fromUsername", fromUsername);
        }
        if (Objects.nonNull(status)) {
            criteria.andEqualTo("status", status);
        }
        if (Objects.nonNull(title)) {
            criteria.andLike("title", title);
        }
        List<UserMessage> userMessages = userMessageMapper.selectByExample(example);
        for (UserMessage item : userMessages) {
            UserMessageListVO temp = new UserMessageListVO();
            temp.setId(item.getId());
            temp.setFromUsername(item.getFromUsername());
            temp.setTitle(item.getTitle());
            temp.setTime(item.getTime());
            temp.setStatus(MessageStatusType.getNameByCode(item.getStatus()));
            temp.setText(item.getText());
            results.add(temp);
        }
        return results;
    }

    @Override
    public Integer countByConditions(String toUsername,
                                     String fromUsername, Integer status, String title) {
        Example example = new Example(UserMessage.class);
        example.orderBy("time").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("toUsername", toUsername);
        if (Objects.nonNull(fromUsername)) {
            criteria.andEqualTo("fromUsername", fromUsername);
        }
        if (Objects.nonNull(status)) {
            criteria.andEqualTo("status", status);
        }
        if (Objects.nonNull(title)) {
            criteria.andLike("title", title);
        }
        return userMessageMapper.selectCountByExample(example);
    }
}

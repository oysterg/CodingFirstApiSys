package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.fjut.cf.mapper.ViewJudgeStatusMapper;
import team.fjut.cf.pojo.enums.CodeLanguage;
import team.fjut.cf.pojo.enums.SubmitResult;
import team.fjut.cf.pojo.po.ViewJudgeStatus;
import team.fjut.cf.pojo.vo.StatusListVO;
import team.fjut.cf.service.ViewJudgeStatusService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author axiang [2020/3/5]
 */
@Service
public class ViewJudgeStatusServiceImpl implements ViewJudgeStatusService {
    @Autowired
    ViewJudgeStatusMapper viewJudgeStatusMapper;

    @Override
    public List<StatusListVO> pagesByConditions(Integer pageNum, Integer pageSize,
                                                Integer contestId,
                                                String nickname, Integer problemId, Integer result, Integer language) {
        List<StatusListVO> results = new ArrayList<>();
        Example example = new Example(ViewJudgeStatus.class);
        // 按id倒序
        example.orderBy("id").desc();
        Example.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(contestId)) {
            criteria.andEqualTo("contestId", contestId);
        }
        if (Objects.nonNull(nickname)) {
            criteria.andLike("nickname", nickname);
        }
        if (Objects.nonNull(problemId)) {
            criteria.andEqualTo("problemId", problemId);
        }
        if (Objects.nonNull(result)) {
            criteria.andEqualTo("result", result);
        }
        if (Objects.nonNull(language)) {
            criteria.andEqualTo("language", language);
        }
        PageHelper.startPage(pageNum, pageSize, false);
        List<ViewJudgeStatus> viewJudgeStatuses = viewJudgeStatusMapper.selectByExample(example);
        for (ViewJudgeStatus item : viewJudgeStatuses) {
            StatusListVO temp = new StatusListVO();
            temp.setId(item.getId());
            temp.setNickname(item.getNickname());
            temp.setProblemId(item.getProblemId().toString());
            // 如果是Score的，带上分值
            if (item.getResult() == SubmitResult.SC.getCode()) {
                temp.setResult(SubmitResult.getNameByCode(item.getResult()) + " " + item.getScore());
            } else {
                temp.setResult(SubmitResult.getNameByCode(item.getResult()));
            }
            temp.setLanguage(CodeLanguage.getNameByCode(item.getLanguage()));
            temp.setTimeUsed(item.getTimeUsed());
            temp.setMemoryUsed(item.getMemoryUsed());
            temp.setCodeLength(item.getCodeLength());
            temp.setSubmitTime(item.getSubmitTime());
            results.add(temp);
        }
        return results;
    }

    @Override
    public int countByConditions(Integer contestId, String nickname, Integer problemId, Integer result, Integer language) {
        Example example = new Example(ViewJudgeStatus.class);
        Example.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(contestId)) {
            criteria.andEqualTo("contestId", contestId);
        }
        if (Objects.nonNull(nickname)) {
            criteria.andLike("nickname", nickname);
        }
        if (Objects.nonNull(problemId)) {
            criteria.andEqualTo("problemId", problemId);
        }
        if (Objects.nonNull(result)) {
            criteria.andEqualTo("result", result);
        }
        if (Objects.nonNull(language)) {
            criteria.andEqualTo("language", language);
        }
        int i = viewJudgeStatusMapper.selectCountByExample(example);
        return i;
    }
}

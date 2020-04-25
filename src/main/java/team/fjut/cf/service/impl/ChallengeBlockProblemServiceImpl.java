package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import team.fjut.cf.mapper.ChallengeBlockProblemMapper;
import team.fjut.cf.mapper.UserProblemSolvedMapper;
import team.fjut.cf.pojo.po.ChallengeBlockProblemPO;
import team.fjut.cf.pojo.po.UserProblemSolved;
import team.fjut.cf.pojo.vo.ChallengeBlockProblemAdminVO;
import team.fjut.cf.pojo.vo.ChallengeBlockProblemVO;
import team.fjut.cf.service.ChallengeBlockProblemService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author axiang [2019/11/11]
 */
@Service
public class ChallengeBlockProblemServiceImpl implements ChallengeBlockProblemService {
    @Resource
    ChallengeBlockProblemMapper challengeBlockProblemMapper;

    @Resource
    UserProblemSolvedMapper userProblemSolvedMapper;

    @Override
    public List<ChallengeBlockProblemVO> pagesByBlockId(String username, Integer blockId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChallengeBlockProblemVO> challengeBlockProblemVOS = challengeBlockProblemMapper.allAsVO(blockId);
        List<UserProblemSolved> userSolvedProblems = userProblemSolvedMapper.selectByUsername(username);
        Map<Integer, Integer> map = new TreeMap<>();
        for (UserProblemSolved solvedProblem : userSolvedProblems) {
            map.put(solvedProblem.getProblemId(), solvedProblem.getSolvedCount());
        }
        for (ChallengeBlockProblemVO challengeBlockProblemVO : challengeBlockProblemVOS) {
            String isSolved = "";
            if (map.get(challengeBlockProblemVO.getProblemId()) == null) {
                isSolved = "";
            } else if (map.get(challengeBlockProblemVO.getProblemId()) >= 1) {
                isSolved = "✔";
            } else {
                isSolved = "X";
            }
            challengeBlockProblemVO.setIsSolved(isSolved);
        }
        return challengeBlockProblemVOS;
    }

    @Override
    public Integer selectCountByBlockId(Integer blockId) {
        return challengeBlockProblemMapper.allCount(blockId);
    }

    // add by zhongml [2020/4/24]
    @Override
    public List<ChallengeBlockProblemVO> selectProblemByBlockId(Integer pageNum, Integer pageSize, String sort, Integer blockId) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChallengeBlockProblemVO> results = challengeBlockProblemMapper.allAsVO(blockId);
        if(sort != null && sort.equals("descending")) {
            Collections.reverse(results);
        }
        return results;
    }

    // add by zhongml [2020/4/24]
    @Override
    public int countProblemByBlockId(Integer blockId) {
        return challengeBlockProblemMapper.allCount(blockId);
    }

    // add by zhongml [2020/4/24]
    @Override
    public int insertProblems(Integer blockId, List<ChallengeBlockProblemAdminVO> challengeProblems) {
        return challengeBlockProblemMapper.insertProblems(blockId, challengeProblems);
    }

    @Override
    public int deleteProblems(Integer blockId) {
        Example example = new Example(ChallengeBlockProblemPO.class);
        example.createCriteria().andEqualTo("blockId", blockId);
        return challengeBlockProblemMapper.deleteByExample(example);
    }
}

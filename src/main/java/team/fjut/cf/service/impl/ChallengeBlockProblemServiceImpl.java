package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import team.fjut.cf.mapper.ChallengeBlockProblemMapper;
import team.fjut.cf.mapper.UserProblemSolvedMapper;
import team.fjut.cf.pojo.po.UserProblemSolvedPO;
import team.fjut.cf.pojo.vo.ChallengeBlockProblemVO;
import team.fjut.cf.service.ChallengeBlockProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author axiang [2019/11/11]
 */
@Service
public class ChallengeBlockProblemServiceImpl implements ChallengeBlockProblemService {
    @Autowired
    ChallengeBlockProblemMapper challengeBlockProblemMapper;

    @Autowired
    UserProblemSolvedMapper userProblemSolvedMapper;

    @Override
    public List<ChallengeBlockProblemVO> pagesByBlockId(String username, Integer blockId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ChallengeBlockProblemVO> challengeBlockProblemVOS = challengeBlockProblemMapper.selectAllAsVO(blockId);
        List<UserProblemSolvedPO> userSolvedProblems = userProblemSolvedMapper.selectByUsername(username);
        Map<Integer, Integer> map = new TreeMap<>();
        for (UserProblemSolvedPO solvedProblem : userSolvedProblems) {
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
        return challengeBlockProblemMapper.selectAllCount(blockId);
    }
}

package team.fjut.cf.service.impl;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import team.fjut.cf.mapper.*;
import team.fjut.cf.pojo.enums.OjId;
import team.fjut.cf.pojo.enums.ProblemDifficultLevel;
import team.fjut.cf.pojo.enums.ProblemType;
import team.fjut.cf.pojo.po.*;
import team.fjut.cf.pojo.vo.ProblemListVO;
import team.fjut.cf.pojo.vo.UserRadarVO;
import team.fjut.cf.service.ProblemService;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @author axiang [2019/10/22]
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    ProblemInfoMapper problemInfoMapper;

    @Autowired
    ProblemViewMapper problemViewMapper;

    @Autowired
    ProblemSampleMapper problemSampleMapper;

    @Autowired
    ProblemDifficultMapper problemDifficultMapper;

    @Autowired
    ProblemMapper problemMapper;

    @Autowired
    UserProblemSolvedMapper userProblemSolvedMapper;

    @Autowired
    ProblemTagRecordMapper problemTagRecordMapper;


    @Override
    public List<ProblemListVO> pagesByConditions(String username, String title, Integer tagId, Integer startIndex, Integer pageSize) {
        List<ProblemListVO> problemListVOS = new ArrayList<>();
        boolean needSolvedStatus = false;
        List<ProblemInfoWithDifficultPO> problemInfoWithDifficultPOS = problemMapper.pagesFullProblemInfo(title, tagId, startIndex, pageSize);
        Map<Integer, Integer> map = new TreeMap<>();
        if (!StringUtils.isEmpty(username)) {
            needSolvedStatus = true;
            List<UserProblemSolvedPO> solvedProblems = userProblemSolvedMapper.selectByUsername(username);

            for (UserProblemSolvedPO solvedProblem : solvedProblems) {
                map.put(solvedProblem.getProblemId(), solvedProblem.getSolvedCount());
            }
        }
        for (ProblemInfoWithDifficultPO problemInfo : problemInfoWithDifficultPOS) {
            ProblemListVO problemListVO = new ProblemListVO();
            String isSolved = "";
            if (needSolvedStatus) {
                if (map.get(problemInfo.getProblemId()) == null) {
                    isSolved = "";
                } else if (map.get(problemInfo.getProblemId()) >= 1) {
                    isSolved = "✔";
                } else {
                    isSolved = "X";
                }
            }
            problemListVO.setIsSolved(isSolved);
            problemListVO.setProblemId(problemInfo.getProblemId());
            problemListVO.setTitle(problemInfo.getTitle());
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String ratio;
            if (problemInfo.getTotalSubmit() == 0) {
                ratio = "0.00%(0/0)";
            } else {
                ratio = decimalFormat.format(100.00 * problemInfo.getTotalAc() / problemInfo.getTotalSubmit()) + "%("
                        + problemInfo.getTotalAc() + "/" + problemInfo.getTotalSubmit() + ")";
            }
            problemListVO.setRatio(ratio);
            problemListVO.setDifficult(ProblemDifficultLevel.getNameByCode(problemInfo.getDifficultLevel()));
            problemListVO.setBelongToOj(OjId.getNameByCode(problemInfo.getBelongOjId()));
            problemListVOS.add(problemListVO);
        }
        return problemListVOS;
    }

    @Override
    public Integer selectCountByConditions(String title, Integer tagId) {
        return problemInfoMapper.selectCountByConditions(title, tagId);
    }

    @Override
    public ProblemInfoPO selectProblemInfoByProblemId(Integer problemId) {
        return problemInfoMapper.queryByProblemId(problemId);
    }

    @Override
    public ProblemViewPO selectProblemViewByProblemId(Integer problemId) {
        return problemViewMapper.selectByProblemId(problemId);
    }

    @Override
    public List<ProblemSamplePO> selectProblemSampleByProblemId(Integer problemId) {
        return problemSampleMapper.selectByProblemId(problemId);
    }


    @Override
    public List<UserRadarVO> selectUserProblemRadarByUsername(String username) {
        List<ProblemTypeCountPO> problemTypeCounts = problemDifficultMapper.selectCountType();
        List<ProblemTypeCountPO> userProblemTypeCounts = userProblemSolvedMapper.selectCountTypeByUsername(username);

        List<UserRadarVO> results = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        map.put(5, 0);
        map.put(6, 0);
        for (ProblemTypeCountPO userPtc : userProblemTypeCounts) {
            map.put(userPtc.getProblemType(), userPtc.getTotalCount() == null ? 0 : userPtc.getTotalCount());
        }

        for (ProblemTypeCountPO ptc : problemTypeCounts) {
            UserRadarVO userRadarVO = new UserRadarVO();
            userRadarVO.setType(ProblemType.getNameByID(ptc.getProblemType()));
            userRadarVO.setScore(100 * map.get(ptc.getProblemType()) / ptc.getTotalCount());
            results.add(userRadarVO);
        }
        return results;
    }

    @Override
    public List<ProblemInfoPO> selectRecommendProblemsByUsername(String username) {
        List<ProblemInfoPO> problemInfoPOS = problemInfoMapper.queryUnSolvedProblemsByUsername(username);
        List<ProblemInfoPO> results = new ArrayList<>();
        int totalUnsolved = problemInfoPOS.size();
        // 如果用户未解决题目小于3，则直接返回推荐题目内容
        if(totalUnsolved <=3)
        {
            return problemInfoPOS;
        }
        // 如果大于3道，随机推荐3道
        // TODO: 可以做更多的操作
        Random random = new Random();
        Set<Integer> set = new TreeSet<>();
        while(true)
        {
            int randomInt = random.nextInt(totalUnsolved);
            set.add(randomInt);
            if(set.size()==3)
            {
                break;
            }
        }
        for (Integer i : set) {
            results.add(problemInfoPOS.get(i));
        }
        return results;

    }
}

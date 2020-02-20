package team.fjut.cf.service.impl;

import com.github.pagehelper.PageHelper;
import team.fjut.cf.mapper.ContestInfoMapper;
import team.fjut.cf.pojo.enums.ContestKind;
import team.fjut.cf.pojo.enums.ContestPermission;
import team.fjut.cf.pojo.po.ContestInfoPO;
import team.fjut.cf.pojo.vo.ContestListVO;
import team.fjut.cf.service.ContestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author axiang [2019/11/18]
 */
@Service
public class ContestInfoServiceImpl implements ContestInfoService {
    @Autowired
    ContestInfoMapper contestInfoMapper;

    @Override
    public List<ContestListVO> pagesByConditions(Integer kind,
                                                 String searchTitle,
                                                 Integer searchPermission,
                                                 Integer searchStatus,
                                                 Integer pageNum,
                                                 Integer pageSize) {
        List<ContestListVO> result = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<ContestInfoPO> contestInfos = contestInfoMapper.selectAllByConditions(kind, searchTitle, searchPermission, searchStatus);
        for (ContestInfoPO contestInfo : contestInfos) {
            ContestListVO contestList = new ContestListVO();
            contestList.setId(contestInfo.getContestId());
            contestList.setTitle(contestInfo.getTitle());
            contestList.setKind(ContestKind.getNameByCode(contestInfo.getContestKind()));
            contestList.setBeginTime(contestInfo.getBeginTime());
            contestList.setEndTime(contestInfo.getEndTime());
            contestList.setPermission(ContestPermission.getNameByCode(contestInfo.getPermissionType()));
            Date currentTime = new Date();
            if (contestInfo.getEndTime().compareTo(currentTime) < 0) {
                contestList.setStatus("已结束");
            } else if (contestInfo.getBeginTime().compareTo(currentTime) >= 0) {
                contestList.setStatus("未开始");
            } else {
                contestList.setStatus("正在进行");
            }
            result.add(contestList);
        }
        return result;
    }

    @Override
    public Integer selectCountByConditions(Integer kind,
                                           String searchTitle,
                                           Integer searchPermission,
                                           Integer searchStatus) {
        return contestInfoMapper.selectCountByConditions(kind, searchTitle, searchPermission, searchStatus);
    }

    @Override
    public ContestInfoPO selectByContestId(Integer contestId) {
        return contestInfoMapper.selectByContestId(contestId);
    }


}

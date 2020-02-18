package team.fjut.cf.service.impl;

import team.fjut.cf.mapper.BorderHonorRankMapper;
import team.fjut.cf.pojo.enums.AwardLevel;
import team.fjut.cf.pojo.enums.ContestLevel;
import team.fjut.cf.pojo.po.BorderHonorRankPO;
import team.fjut.cf.pojo.vo.BorderHonorRankVO;
import team.fjut.cf.service.BorderHonorRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
@Service
public class BorderHonorRankServiceImpl implements BorderHonorRankService {
    @Autowired
    BorderHonorRankMapper borderHonorRankMapper;

    @Override
    public List<BorderHonorRankVO> pages(Integer startIndex, Integer pageSize) {
        List<BorderHonorRankVO> results = new ArrayList<>();
        List<BorderHonorRankPO> borderHonorRankPOS = borderHonorRankMapper.pages(startIndex, pageSize);
        for (BorderHonorRankPO item : borderHonorRankPOS) {
            BorderHonorRankVO borderHonorRankVO = new BorderHonorRankVO();
            borderHonorRankVO.setContestLevel(ContestLevel.getNameByCode(item.getContestLevel()));
            borderHonorRankVO.setAwardLevel(AwardLevel.getNameByCode(item.getAwardLevel()));
            borderHonorRankVO.setDescription(item.getDescription());
            borderHonorRankVO.setId(item.getId());
            borderHonorRankVO.setRealNameOne(item.getRealNameOne());
            borderHonorRankVO.setRealNameTwo(item.getRealNameTwo());
            borderHonorRankVO.setRealNameThree(item.getRealNameThree());
            borderHonorRankVO.setUsernameOne(item.getUsernameOne());
            borderHonorRankVO.setUsernameTwo(item.getUsernameTwo());
            borderHonorRankVO.setUsernameThree(item.getUsernameThree());
            borderHonorRankVO.setRewardDate(item.getRewardDate());
            borderHonorRankVO.setRegisterTime(item.getRegisterTime());
            results.add(borderHonorRankVO);
        }
        return results;
    }

    @Override
    public Integer selectAllCount() {
        return borderHonorRankMapper.selectAllCount();
    }

    @Override
    public List<String> selectByUsername(String username) {
        List<String> result = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<BorderHonorRankPO> borderHonorRankPOS = borderHonorRankMapper.selectByUsername(username);
        for (BorderHonorRankPO honorRankPO : borderHonorRankPOS) {
            String str = "";
            str = format.format(honorRankPO.getRewardDate()) + ": 参加 " +
                    ContestLevel.getNameByCode(honorRankPO.getContestLevel())
                    + " 获得 " + AwardLevel.getNameByCode(honorRankPO.getAwardLevel())
                    + " " + honorRankPO.getDescription();
            result.add(str);
        }
        return result;
    }
}

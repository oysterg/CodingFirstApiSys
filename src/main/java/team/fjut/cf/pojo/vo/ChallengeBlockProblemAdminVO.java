package team.fjut.cf.pojo.vo;

import lombok.Data;

/**
 * @author zhongml [2020/4/24]
 */
@Data
public class ChallengeBlockProblemAdminVO {
    private String title;
    private Integer problemOrder;
    private Integer problemId;
    private Integer score;
    private Integer rewardAcb;
}

package team.fjut.cf.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhongml [2020/4/24]
 */
@NoArgsConstructor
@Data
public class ChallengeBlockAdminVO {
    private Integer id;
    private String name;
    private String blockType;
    private String description;
    private Integer preconditionTotalScore;
    private List<ChallengeBlockConditionVO> preconditionBlocks;
    private List<ChallengeBlockProblemAdminVO> challengeProblems;
}

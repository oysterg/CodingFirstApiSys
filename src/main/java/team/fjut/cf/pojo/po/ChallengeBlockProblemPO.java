package team.fjut.cf.pojo.po;

import javax.persistence.Table;

/**
 * @author axiang [2019/10/21]
 */
@Table(name = "t_challenge_block_problem")
public class ChallengeBlockProblemPO {
    private Integer id;
    private Integer blockId;
    private Integer problemOrder;
    private Integer problemId;
    private Integer score;
    private Integer rewardAcb;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public Integer getProblemOrder() {
        return problemOrder;
    }

    public void setProblemOrder(Integer problemOrder) {
        this.problemOrder = problemOrder;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRewardAcb() {
        return rewardAcb;
    }

    public void setRewardAcb(Integer rewardAcb) {
        this.rewardAcb = rewardAcb;
    }

    @Override
    public String toString() {
        return "ChallengeBlockProblemPO{" +
                "id=" + id +
                ", blockId=" + blockId +
                ", problemOrder=" + problemOrder +
                ", problemId=" + problemId +
                ", totalScore=" + score +
                ", rewardAcb=" + rewardAcb +
                '}';
    }
}

package team.fjut.cf.pojo.po;

/**
 * @author axiang [2019/10/21]
 */
public class ChallengeBlockConditionPO {
    private Integer id;
    private Integer blockId;
    private Integer preconditionBlockId;
    private Integer preconditionUnlockScore;

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

    public Integer getPreconditionBlockId() {
        return preconditionBlockId;
    }

    public void setPreconditionBlockId(Integer preconditionBlockId) {
        this.preconditionBlockId = preconditionBlockId;
    }

    public Integer getPreconditionUnlockScore() {
        return preconditionUnlockScore;
    }

    public void setPreconditionUnlockScore(Integer preconditionUnlockScore) {
        this.preconditionUnlockScore = preconditionUnlockScore;
    }

    @Override
    public String toString() {
        return "ChallengeBlockCondition{" +
                "id=" + id +
                ", blockId=" + blockId +
                ", preconditionBlockId=" + preconditionBlockId +
                ", preconditionUnlockScore=" + preconditionUnlockScore +
                '}';
    }
}

package team.fjut.cf.pojo.vo;

/**
 * @author axiang [2019/11/11]
 */
public class ChallengeBlockProblemVO {
    private Integer id;
    private Integer blockId;
    private String isSolved;
    private String title;
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

    public String getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(String isSolved) {
        this.isSolved = isSolved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "ChallengeBlockProblemVO{" +
                "id=" + id +
                ", blockId=" + blockId +
                ", isSolved='" + isSolved + '\'' +
                ", title='" + title + '\'' +
                ", problemOrder=" + problemOrder +
                ", problemId=" + problemId +
                ", score=" + score +
                ", rewardAcb=" + rewardAcb +
                '}';
    }
}

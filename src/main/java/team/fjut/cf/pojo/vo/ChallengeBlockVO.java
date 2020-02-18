package team.fjut.cf.pojo.vo;

/**
 * @author axiang [2019/11/11]
 */
public class ChallengeBlockVO {
    private Integer id;
    private String name;
    private String blockType;
    private String description;
    private Integer getScore;
    private Integer totalScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlockType() {
        return blockType;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGetScore() {
        return getScore;
    }

    public void setGetScore(Integer getScore) {
        this.getScore = getScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "ChallengeBlockVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blockType='" + blockType + '\'' +
                ", description='" + description + '\'' +
                ", getScore=" + getScore +
                ", totalScore=" + totalScore +
                '}';
    }
}

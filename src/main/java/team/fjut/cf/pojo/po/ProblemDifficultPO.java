package team.fjut.cf.pojo.po;

/**
 * @author axiang [2019/10/18]
 */
public class ProblemDifficultPO {
    private Integer id;
    private Integer problemId;
    private Integer difficultLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getDifficultLevel() {
        return difficultLevel;
    }

    public void setDifficultLevel(Integer difficultLevel) {
        this.difficultLevel = difficultLevel;
    }

    @Override
    public String toString() {
        return "ProblemDifficultPO{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", difficultLevel=" + difficultLevel +
                '}';
    }
}

package team.fjut.cf.pojo.po;

/**
 * @author axiang [2019/10/18]
 */
public class JudgeResultPO {
    private Integer id;
    private Integer judgeId;
    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(Integer judgeId) {
        this.judgeId = judgeId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "JudgeResultPO{" +
                "id=" + id +
                ", judgeId=" + judgeId +
                ", info='" + info + '\'' +
                '}';
    }
}

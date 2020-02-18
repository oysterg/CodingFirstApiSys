package team.fjut.cf.pojo.po;

/**
 * @author axiang [2019/10/18]
 */
public class ProblemInfoPO {
    private Integer id;
    private Integer problemId;
    private String title;
    private Integer belongOjId;
    private String belongProblemId;
    private String author;
    private Integer totalSubmit;
    private Integer totalAc;
    private Integer totalSubmitUser;
    private Integer totalAcUser;
    private Integer visible;
    private Integer judgeOption;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBelongOjId() {
        return belongOjId;
    }

    public void setBelongOjId(Integer belongOjId) {
        this.belongOjId = belongOjId;
    }

    public String getBelongProblemId() {
        return belongProblemId;
    }

    public void setBelongProblemId(String belongProblemId) {
        this.belongProblemId = belongProblemId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTotalSubmit() {
        return totalSubmit;
    }

    public void setTotalSubmit(Integer totalSubmit) {
        this.totalSubmit = totalSubmit;
    }

    public Integer getTotalAc() {
        return totalAc;
    }

    public void setTotalAc(Integer totalAc) {
        this.totalAc = totalAc;
    }

    public Integer getTotalSubmitUser() {
        return totalSubmitUser;
    }

    public void setTotalSubmitUser(Integer totalSubmitUser) {
        this.totalSubmitUser = totalSubmitUser;
    }

    public Integer getTotalAcUser() {
        return totalAcUser;
    }

    public void setTotalAcUser(Integer totalAcUser) {
        this.totalAcUser = totalAcUser;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getJudgeOption() {
        return judgeOption;
    }

    public void setJudgeOption(Integer judgeOption) {
        this.judgeOption = judgeOption;
    }

    @Override
    public String toString() {
        return "ProblemInfoPO{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", title='" + title + '\'' +
                ", belongOjId=" + belongOjId +
                ", belongProblemId='" + belongProblemId + '\'' +
                ", author='" + author + '\'' +
                ", totalSubmit=" + totalSubmit +
                ", totalAc=" + totalAc +
                ", totalSubmitUser=" + totalSubmitUser +
                ", totalAcUser=" + totalAcUser +
                ", visible=" + visible +
                ", judgeOption=" + judgeOption +
                '}';
    }
}

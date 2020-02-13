package com.fjut.cf.pojo.vo;

/**
 * @author axiang [2019/10/22]
 */
public class ProblemListVO {
    String isSolved;
    Integer problemId;
    String title;
    String ratio;
    String difficult;
    String belongToOj;

    public String getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(String isSolved) {
        this.isSolved = isSolved;
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

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public String getBelongToOj() {
        return belongToOj;
    }

    public void setBelongToOj(String belongToOj) {
        this.belongToOj = belongToOj;
    }

    @Override
    public String toString() {
        return "ProblemListVO{" +
                "isSolved=" + isSolved +
                ", problemId=" + problemId +
                ", title='" + title + '\'' +
                ", ratio='" + ratio + '\'' +
                ", difficult='" + difficult + '\'' +
                ", belongToOj='" + belongToOj + '\'' +
                '}';
    }
}

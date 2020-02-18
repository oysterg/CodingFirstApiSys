package team.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/10/18]
 */
public class ContestRegisterUserPO {
    private Integer id;
    private Integer contestId;
    private String username;
    private Date registerTime;
    private Integer reviewStatus;
    private String reviewInfo;
    private Date reviewTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewInfo() {
        return reviewInfo;
    }

    public void setReviewInfo(String reviewInfo) {
        this.reviewInfo = reviewInfo;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Override
    public String toString() {
        return "ContestRegisterUser{" +
                "id=" + id +
                ", contestId=" + contestId +
                ", username='" + username + '\'' +
                ", registerTime=" + registerTime +
                ", reviewStatus=" + reviewStatus +
                ", reviewInfo='" + reviewInfo + '\'' +
                ", reviewTime=" + reviewTime +
                '}';
    }
}

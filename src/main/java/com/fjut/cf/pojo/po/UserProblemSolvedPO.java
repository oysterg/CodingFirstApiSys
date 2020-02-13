package com.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/11/11]
 */
public class UserProblemSolvedPO {
    private Integer id;
    private String username;
    private Integer problemId;
    private Integer tryCount;
    private Integer solvedCount;
    private Date lastTryTime;
    private Date firstSolvedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public Integer getSolvedCount() {
        return solvedCount;
    }

    public void setSolvedCount(Integer solvedCount) {
        this.solvedCount = solvedCount;
    }

    public Date getLastTryTime() {
        return lastTryTime;
    }

    public void setLastTryTime(Date lastTryTime) {
        this.lastTryTime = lastTryTime;
    }

    public Date getFirstSolvedTime() {
        return firstSolvedTime;
    }

    public void setFirstSolvedTime(Date firstSolvedTime) {
        this.firstSolvedTime = firstSolvedTime;
    }

    @Override
    public String toString() {
        return "UserProblemSolvedPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", problemId=" + problemId +
                ", tryCount=" + tryCount +
                ", solvedCount=" + solvedCount +
                ", lastTryCount=" + lastTryTime +
                ", firstTryCount=" + firstSolvedTime +
                '}';
    }
}

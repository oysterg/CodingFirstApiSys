package com.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
public class ProblemStarPO {
    private Integer id;
    private Integer problemId;
    private String username;
    private Date time;
    private String mark;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "ProblemStarPO{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", username='" + username + '\'' +
                ", time=" + time +
                ", mark='" + mark + '\'' +
                '}';
    }
}

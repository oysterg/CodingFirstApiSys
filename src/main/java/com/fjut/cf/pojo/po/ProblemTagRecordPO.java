package com.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
public class ProblemTagRecordPO {
    private Integer id;
    private String username;
    private Integer tagId;
    private Date time;
    private Double confidence;

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

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "ProblemTagRecordPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", tagId=" + tagId +
                ", time=" + time +
                ", confidence=" + confidence +
                '}';
    }
}

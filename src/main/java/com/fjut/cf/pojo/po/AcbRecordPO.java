package com.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
public class AcbRecordPO {
    private Integer id;
    private String username;
    private Integer acbChange;
    private Integer reason;
    private Date time;
    private String mark;

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

    public Integer getAcbChange() {
        return acbChange;
    }

    public void setAcbChange(Integer acbChange) {
        this.acbChange = acbChange;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
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
        return "AcbRecordPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", acbChange=" + acbChange +
                ", reason=" + reason +
                ", time=" + time +
                ", mark='" + mark + '\'' +
                '}';
    }
}

package com.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/11/11]
 */
public class UserMessagePO {
    private Integer id;
    private String username;
    private Integer status;
    private String title;
    private String text;
    private Date time;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserMessagePO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}

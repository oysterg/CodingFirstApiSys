package team.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/11/12]
 */
public class UserTitleRecordPO {
    private Integer id;
    private String username;
    private Integer titleId;
    private Date obtainTime;
    private Date expiredTime;

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

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public Date getObtainTime() {
        return obtainTime;
    }

    public void setObtainTime(Date obtainTime) {
        this.obtainTime = obtainTime;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    @Override
    public String toString() {
        return "UserTitleRecordPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", titleId=" + titleId +
                ", obtainTime=" + obtainTime +
                ", expiredTime=" + expiredTime +
                '}';
    }
}

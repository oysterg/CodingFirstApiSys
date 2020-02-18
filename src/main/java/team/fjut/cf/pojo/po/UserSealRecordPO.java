package team.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/11/12]
 */
public class UserSealRecordPO {
    private Integer id;
    private String username;
    private Integer sealId;
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

    public Integer getSealId() {
        return sealId;
    }

    public void setSealId(Integer sealId) {
        this.sealId = sealId;
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
        return "UserSealRecordPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sealId=" + sealId +
                ", obtainTime=" + obtainTime +
                ", expiredTime=" + expiredTime +
                '}';
    }
}

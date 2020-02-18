package team.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/10/18]
 */
public class UserCheckInPO {
    private Integer id;
    private String username;
    private Date checkTime;
    private String info;
    private String ipAddress;

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

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "UserCheckInPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", checkTime=" + checkTime +
                ", info='" + info + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}

package team.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
public class ChallengeUserOpenBlockPO {
    private Integer id;
    private String username;
    private Integer blockId;
    private Date unlockTime;

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

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public Date getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(Date unlockTime) {
        this.unlockTime = unlockTime;
    }

    @Override
    public String toString() {
        return "ChallengeUserOpenBlockPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", blockId=" + blockId +
                ", unlockTime=" + unlockTime +
                '}';
    }
}

package team.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
public class BorderHonorRankPO {
    private Integer id;
    private String usernameOne;
    private String realNameOne;
    private String usernameTwo;
    private String realNameTwo;
    private String usernameThree;
    private String realNameThree;
    private Date rewardDate;
    private Date registerTime;
    private Integer contestLevel;
    private Integer awardLevel;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsernameOne() {
        return usernameOne;
    }

    public void setUsernameOne(String usernameOne) {
        this.usernameOne = usernameOne;
    }

    public String getRealNameOne() {
        return realNameOne;
    }

    public void setRealNameOne(String realNameOne) {
        this.realNameOne = realNameOne;
    }

    public String getUsernameTwo() {
        return usernameTwo;
    }

    public void setUsernameTwo(String usernameTwo) {
        this.usernameTwo = usernameTwo;
    }

    public String getRealNameTwo() {
        return realNameTwo;
    }

    public void setRealNameTwo(String realNameTwo) {
        this.realNameTwo = realNameTwo;
    }

    public String getUsernameThree() {
        return usernameThree;
    }

    public void setUsernameThree(String usernameThree) {
        this.usernameThree = usernameThree;
    }

    public String getRealNameThree() {
        return realNameThree;
    }

    public void setRealNameThree(String realNameThree) {
        this.realNameThree = realNameThree;
    }

    public Date getRewardDate() {
        return rewardDate;
    }

    public void setRewardDate(Date rewardDate) {
        this.rewardDate = rewardDate;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getContestLevel() {
        return contestLevel;
    }

    public void setContestLevel(Integer contestLevel) {
        this.contestLevel = contestLevel;
    }

    public Integer getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(Integer awardLevel) {
        this.awardLevel = awardLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BorderHonorRankPO{" +
                "id=" + id +
                ", usernameOne='" + usernameOne + '\'' +
                ", realNameOne='" + realNameOne + '\'' +
                ", usernameTwo='" + usernameTwo + '\'' +
                ", realNameTwo='" + realNameTwo + '\'' +
                ", usernameThree='" + usernameThree + '\'' +
                ", realNameThree='" + realNameThree + '\'' +
                ", rewardDate=" + rewardDate +
                ", registerTime=" + registerTime +
                ", contestLevel=" + contestLevel +
                ", awardLevel=" + awardLevel +
                ", description='" + description + '\'' +
                '}';
    }
}

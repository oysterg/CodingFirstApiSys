package com.fjut.cf.pojo.vo;

/**
 * @author axiang [2019/11/14]
 */
public class UserRadarVO {
    private String username;
    private String type;
    private Integer score;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "UserRadarVO{" +
                "username='" + username + '\'' +
                ", type='" + type + '\'' +
                ", score=" + score +
                '}';
    }
}

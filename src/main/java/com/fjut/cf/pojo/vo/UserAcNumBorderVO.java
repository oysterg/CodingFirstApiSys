package com.fjut.cf.pojo.vo;

/**
 * @author axiang [2019/10/30]
 */
public class UserAcNumBorderVO {
    private String username;
    private String nick;
    private Integer acNum;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getAcNum() {
        return acNum;
    }

    public void setAcNum(Integer acNum) {
        this.acNum = acNum;
    }

    @Override
    public String toString() {
        return "UserAcbBorderVO{" +
                "username='" + username + '\'' +
                ", nick='" + nick + '\'' +
                ", acb=" + acNum +
                '}';
    }
}

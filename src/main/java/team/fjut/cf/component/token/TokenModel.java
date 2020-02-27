package team.fjut.cf.component.token;

/**
 * Token的Model类
 *
 * @author axiang [2019/7/5]
 */
public class TokenModel {
    private String username;
    private String role;
    private String ip;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}

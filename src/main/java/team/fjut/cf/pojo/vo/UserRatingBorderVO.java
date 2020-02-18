package team.fjut.cf.pojo.vo;

/**
 * @author axiang [2019/10/30]
 */
public class UserRatingBorderVO {
    private String username;
    private String nick;
    private Integer rating;

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "UserAcbBorderVO{" +
                "username='" + username + '\'' +
                ", nick='" + nick + '\'' +
                ", acb=" + rating +
                '}';
    }
}

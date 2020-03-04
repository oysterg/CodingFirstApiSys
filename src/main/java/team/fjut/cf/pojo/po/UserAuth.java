package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户认证表
 *
 * @author axiang [2019/8/28]
 */
@Data
@Table(name = "t_user_auth")
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    String username;
    String salt;
    String password;
    Integer attemptLoginFailCount;
    Integer locked;
    Date unlockTime;
    Date lastLoginTime;
}

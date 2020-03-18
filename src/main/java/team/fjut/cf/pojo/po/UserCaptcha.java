package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2020/3/18]
 */
@Data
@Table(name = "t_user_captcha")
public class UserCaptcha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    String name;
    String captchaValue;
    Date createTime;
    Date expireTime;
    /**
     * 0 不是游客，1 是游客
     */
    Integer isGuest;
}

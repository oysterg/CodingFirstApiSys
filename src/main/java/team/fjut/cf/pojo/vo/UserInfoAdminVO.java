package team.fjut.cf.pojo.vo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 后台管理用户信息
 *
 * @author zhongml [2020/4/28]
 */
@Data
public class UserInfoAdminVO {
    Integer id;
    String username;
    Integer gender;
    String email;
    String phone;
    String motto;
    Date registerTime;
    Integer rating;
    Integer acb;
    String nickname;
    String avatarUrl;
}

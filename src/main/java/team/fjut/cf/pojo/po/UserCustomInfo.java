package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author axiang [2019/10/14]
 */
@Data
@Table(name = "t_user_custom_info")
public class UserCustomInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    String username;
    String nickname;
    String avatarUrl;
    Integer adjectiveId;
    Integer articleId;
    Integer sealId;
}

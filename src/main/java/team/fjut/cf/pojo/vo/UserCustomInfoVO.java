package team.fjut.cf.pojo.vo;

import lombok.Data;

/**
 * @author axiang [2019/11/12]
 */
@Data
public class UserCustomInfoVO {
    Integer id;
    String username;
    String nickname;
    String avatarUrl;
    String adjectiveTitle;
    String articleTitle;
    String sealUrl;
}

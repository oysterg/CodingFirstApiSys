package team.fjut.cf.pojo.po;

import lombok.Data;

/**
 * @author axiang [2019/10/14]
 */
@Data
public class UserCustomInfoPO {
    Integer id;
    String username;
    String nickname;
    String avatarUrl;
    Integer adjectiveId;
    Integer articleId;
    Integer sealId;
}

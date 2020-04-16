package team.fjut.cf.component.token;

import lombok.Data;

/**
 * Token的Model类
 *
 * @author axiang [2019/7/5]
 */
@Data
public class TokenModel {
    String username;
    String role;
    String ip;
}

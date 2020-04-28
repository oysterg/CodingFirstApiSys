package team.fjut.cf.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author zhongml [2020/4/28]
 */
@Data
public class UserTitleInfoVO {
    private Integer id;
    private String name;
    private Integer lifeTime;
    private String pictureUrl;
    private String username;
    private Date obtainTime;
    private Date expiredTime;
}

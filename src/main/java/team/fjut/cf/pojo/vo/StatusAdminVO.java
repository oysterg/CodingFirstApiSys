package team.fjut.cf.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author zhongml [2020/4/27]
 */
@Data
public class StatusAdminVO {
    Integer id;
    String username;
    String nickname;
    Integer problemId;
    Integer contestId;
    String language;
    Date submitTime;
    String result;
    Integer score;
    String timeUsed;
    String memoryUsed;
    String code;
    Integer codeLength;
}

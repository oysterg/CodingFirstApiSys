package team.fjut.cf.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author axiang [2020/3/21]
 */
@Data
public class VjJudgeResultVO {
    Integer id;
    Integer runId;
    String username;
    String nickname;
    String oj;
    String prob;
    String status;
    Integer runtime;
    Integer memory;
    Integer length;
    String language;
    Date submitTime;
}

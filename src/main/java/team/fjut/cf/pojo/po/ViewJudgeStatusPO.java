package team.fjut.cf.pojo.po;

import lombok.Data;

import java.util.Date;

/**
 * 评测视图
 *
 * @author axiang [2019/10/31]
 */
@Data
public class ViewJudgeStatusPO {
    Integer id;
    String nick;
    String username;
    Integer problemId;
    Integer contestId;
    Integer language;
    Date submitTime;
    Integer result;
    Integer score;
    String timeUsed;
    String memoryUsed;
    String code;
    Integer codeLength;
}

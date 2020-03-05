package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * 评测视图
 *
 * @author axiang [2019/10/31]
 */
@Data
@Table(name = "v_judge_status")
public class ViewJudgeStatus {
    Integer id;
    String username;
    Integer problemId;
    Integer contestId;
    Integer language;
    Date submitTime;
    Integer result;
    Integer score;
    String timeUsed;
    String memoryUsed;
    Integer codeLength;
    String nickname;
}

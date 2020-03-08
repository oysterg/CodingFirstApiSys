package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/18]
 */
@Data
@Table(name = "t_judge_status")
public class JudgeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
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
    String code;
    Integer codeLength;


}

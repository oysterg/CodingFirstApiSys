package team.fjut.cf.pojo.vo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/31]
 */
@Data
@Table(name = "t_judge_status")
public class JudgeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    String nick;
    String username;
    Integer problemId;
    String result;
    String language;
    String timeUsed;
    String memoryUsed;
    String code;
    Integer codeLength;
    Date submitTime;


}

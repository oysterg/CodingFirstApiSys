package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2020/3/16]
 */
@Data
@Table(name = "t_vj_judge_result")
public class VjJudgeResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    String username;
    Integer runId;
    String remoteRunId;
    String oj;
    String probNum;
    String author;
    Integer authorId;
    Date submitTime;
    Integer processing;
    Integer statusType;
    String statusCanonical;
    String status;
    String language;
    String languageCanonical;
    String code;
    Integer length;
    String additionalInfo;
    Integer runtime;
    Integer memory;
    Integer isOpen;
}

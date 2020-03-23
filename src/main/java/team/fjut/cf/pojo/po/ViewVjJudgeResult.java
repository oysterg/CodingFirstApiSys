package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2020/3/21]
 */
@Data
@Table(name = "v_vj_judge_result")
public class ViewVjJudgeResult {
    Integer id;
    String username;
    String nickname;
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
}

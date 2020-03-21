package team.fjut.cf.component.judge.vjudge.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author axiang [2020/3/21]
 */
@Data
public class ProblemSolution {
    Integer runId;
    String remoteRunId;
    String oj;
    String probNum;
    String author;
    Integer authorId;
    Date submitTime;
    Boolean processing;
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

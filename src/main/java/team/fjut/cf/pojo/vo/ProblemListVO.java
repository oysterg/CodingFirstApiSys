package team.fjut.cf.pojo.vo;

import lombok.Data;

/**
 * @author axiang [2019/10/22]
 */
@Data
public class ProblemListVO {
    String isSolved;
    Integer problemId;
    String title;
    String ratio;
    String difficult;
    String belongToOj;
}

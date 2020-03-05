package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author axiang [2020/3/5]
 */
@Data
@Table(name = "v_problem_info")
public class ViewProblemInfo {
    Integer id;
    Integer problemId;
    String title;
    Integer belongOjId;
    String belongProblemId;
    String author;
    Integer totalSubmit;
    Integer totalAc;
    Integer totalSubmitUser;
    Integer totalAcUser;
    Integer visible;
    Integer judgeOption;
    Integer problemType;
    Integer difficultLevel;
}

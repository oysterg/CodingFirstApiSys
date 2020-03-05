package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author axiang [2019/10/18]
 */
@Data
@Table(name = "t_problem_info")
public class ProblemInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
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


}

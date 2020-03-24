package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/11/11]
 */
@Data
@Table(name = "t_vj_user_problem_solved")
public class VjUserProblemSolved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    String username;
    String ojId;
    String probNum;
    Integer tryCount;
    Integer solvedCount;
    Date lastTryTime;
    Date firstSolvedTime;
}

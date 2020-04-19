package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
@Data
@Table(name = "t_problem_star")
public class ProblemStarPO {
    private Integer id;
    private Integer problemId;
    private String username;
    private Date time;
    private String mark;
}

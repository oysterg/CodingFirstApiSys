package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author axiang [2019/10/21]
 */
@Data
@Table(name = "t_problem_tag")
public class ProblemTagPO {
    private Integer id;
    private String name;
    private Integer tagType;
    private String createUser;
    private Integer priority;
}

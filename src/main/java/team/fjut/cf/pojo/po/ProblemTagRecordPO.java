package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
@Data
@Table(name = "t_problem_tag_record")
public class ProblemTagRecordPO {
    private Integer id;
    private String username;
    private Integer problemId;
    private Integer tagId;
    private Date time;
    private Double confidence;
}

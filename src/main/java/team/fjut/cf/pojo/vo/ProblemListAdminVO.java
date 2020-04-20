package team.fjut.cf.pojo.vo;

import lombok.Data;

/**
 * @author zhongml [2020/4/17]
 */
@Data
public class ProblemListAdminVO {
    private Integer problemId;
    private String title;
    private String belongOj;
    private String ratio;
    private String author;
    private Integer visible;
    private String difficulty;

}

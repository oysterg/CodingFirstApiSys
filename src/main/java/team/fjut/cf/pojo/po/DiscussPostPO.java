package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
@Data
@Table(name = "t_discuss_post")
public class DiscussPostPO {
    private Integer id;
    private String title;
    private Date time;
    private String author;
    private Double priority;
}

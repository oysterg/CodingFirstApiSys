package team.fjut.cf.pojo.vo;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
@Data
@Table(name = "t_discuss_post")
public class DiscussPostVO {
    private Integer id;
    private String title;
    private Date time;
    private String author;
    private Integer replyNum;
    private Double priority;
}

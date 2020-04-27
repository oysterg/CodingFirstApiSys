package team.fjut.cf.pojo.vo;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author zhongml [2020/4/27]
 */
@Data
public class DiscussReplyPostVO {
    private Integer id;
    private Integer discussId;
    private Integer replyOrder;
    private Date time;
    private String author;
    private String avatarUrl;
    private String text;
}

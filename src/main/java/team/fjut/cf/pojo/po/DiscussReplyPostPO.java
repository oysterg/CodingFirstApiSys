package team.fjut.cf.pojo.po;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
@Table(name = "t_discuss_reply_post")
public class DiscussReplyPostPO {
    private Integer id;
    private Integer discussId;
    private Integer replyOrder;
    private Date time;
    private String author;
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Integer discussId) {
        this.discussId = discussId;
    }

    public Integer getReplyOrder() {
        return replyOrder;
    }

    public void setReplyOrder(Integer replyOrder) {
        this.replyOrder = replyOrder;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "DiscussReplyPostPO{" +
                "id=" + id +
                ", discussId=" + discussId +
                ", replyOrder=" + replyOrder +
                ", time=" + time +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

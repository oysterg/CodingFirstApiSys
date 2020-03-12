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
@Table(name = "t_user_message")
public class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    String toUsername;
    String fromUsername;
    Integer status;
    String title;
    String text;
    Date time;
}

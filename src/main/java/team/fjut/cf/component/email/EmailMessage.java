package team.fjut.cf.component.email;

import lombok.Data;

/**
 * 发送邮件信息类
 * @author axiang [2019/11/28]
 */
@Data
public class EmailMessage {
    String from;
    String to;
    String subject;
    String text;
}

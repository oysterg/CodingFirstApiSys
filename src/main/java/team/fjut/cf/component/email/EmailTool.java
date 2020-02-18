package team.fjut.cf.component.email;

import team.fjut.cf.pojo.bo.SendEmailBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 邮件发送工具类
 *
 * @author axiang [2019/10/30]
 */
@Component
public class EmailTool {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Async
    public void sendEmail(SendEmailBO emailBO)
    {
        SimpleMailMessage message  = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(emailBO.getTo());
        message.setSubject(emailBO.getSubject());
        message.setText(emailBO.getText());
        javaMailSender.send(message);
    }

}

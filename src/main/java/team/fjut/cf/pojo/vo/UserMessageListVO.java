package team.fjut.cf.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author axiang [2020/3/12]
 */
@Data
public class UserMessageListVO {
    Integer id;
    String status;
    Date time;
    String fromUsername;
    String title;
    String text;
}

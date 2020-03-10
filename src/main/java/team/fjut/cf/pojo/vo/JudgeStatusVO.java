package team.fjut.cf.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author axiang [2019/10/31]
 */
@Data
public class JudgeStatusVO {
    Integer id;
    String nickname;
    String username;
    Integer problemId;
    String result;
    String language;
    String timeUsed;
    String memoryUsed;
    String code;
    Integer codeLength;
    Date submitTime;


}

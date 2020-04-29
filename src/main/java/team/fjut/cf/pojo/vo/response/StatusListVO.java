package team.fjut.cf.pojo.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * @author axiang [2020/3/5]
 */
@Data
public class StatusListVO {
    Integer id;
    String nickname;
    String problemId;
    String result;
    String language;
    String timeUsed;
    String memoryUsed;
    Integer codeLength;
    Date submitTime;
}

package team.fjut.cf.component.judge.local.pojo;

import lombok.Data;

/**
 * 提交到本地评测机的参数
 *
 * @author axiang [2019/7/31]
 */
@Data
public class LocalJudgeSubmitInfoParams {
    String type;
    Integer pid;
    Integer rid;
    Integer timeLimit;
    Integer memoryLimit;
    String code;
    Integer languageId;
}

package team.fjut.cf.component.judge.vjudge.pojo;

import lombok.Data;

/**
 * 提交评测参数列表
 *
 * @author axiang [2020/3/16]
 */
@Data
public class SubmitParams {
    String language;
    String share;
    String source;
    String captcha;
    String oj;
    String probNum;
}

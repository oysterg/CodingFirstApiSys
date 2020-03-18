package team.fjut.cf.service;

import team.fjut.cf.pojo.po.UserCaptcha;

/**
 * @author axiang
 */
public interface UserCaptchaService {
    /**
     * 更新或插入一条验证码记录
     *
     * @param userCaptcha
     * @return
     */
    int updateCaptcha(UserCaptcha userCaptcha);

    /**
     * 检查验证码是否对应，返回1为正确，返回0为错误，返回2为过期
     *
     * @param name
     * @param captchaValue
     * @return
     */
    int checkCaptcha(String name, String captchaValue);
}

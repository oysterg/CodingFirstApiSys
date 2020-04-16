package team.fjut.cf.component.token;

/**
 * Token的状态枚举
 * 0：失败
 * 1：成功
 * 2：过期
 *
 * @author axiang [2020/4/16]
 */
public enum TokenStatus {
    /**
     * Token校验失败
     */
    IS_FAIL(0),
    /**
     * Token校验成功
     */
    IS_TRUE(1),
    /**
     * Token过期
     */
    IS_OUTDATED(2),
    /**
     * 是游客
     */
    IS_GUEST(3);

    TokenStatus(int code) {
    }
}

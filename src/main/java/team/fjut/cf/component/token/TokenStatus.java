package team.fjut.cf.component.token;

/**
 * Token的状态枚举
 *
 * @author axiang
 */
public enum TokenStatus {
    IS_FAIL(0),
    IS_TRUE(1),
    IS_OUTDATED(2);

    private int code;

    TokenStatus(int code) {
        this.code = code;
    }
}

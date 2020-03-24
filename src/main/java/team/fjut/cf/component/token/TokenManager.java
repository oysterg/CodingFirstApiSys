package team.fjut.cf.component.token;


/**
 * token 操作 基类
 *
 * @author axiang [2019/10/21]
 */
public interface TokenManager {
    /**
     * 根据传入的TokenModel创建一个token并存入Redis中
     *
     * @param model
     * @return
     */
    String createToken(TokenModel model);

    /**
     * 检查token是否有效，返回token状态枚举
     *
     * @param token
     * @return
     */
    TokenStatus checkToken(String token);

    /**
     * 获取Token内容
     *
     * @param token
     * @return
     */
    TokenModel getTokenModel(String token);

    /**
     * 清除token
     *
     * @param username
     */
    void deleteToken(String username);

}

package com.fjut.cf.component.token;


/**
 * token 操作 基类
 *
 * @author axiang [2019/10/21]
 */

public interface TokenManager {

    /**
     * 创建一个token，关联上指定用户
     *
     * @param username
     * @return
     */
    TokenModel createToken(String username);

    /**
     * 检查token是否有效
     *
     * @param model
     * @return
     */
    boolean checkToken(TokenModel model);

    /**
     * 使用token类生成加密字符串
     *
     * @param model
     * @return
     */
    String createAuth(TokenModel model);


    /**
     * 从加密字符串中获取TokenModel
     *
     * @param auth
     * @return
     */
    TokenModel getToken(String auth);

    /**
     * 清除token
     *
     * @param username
     */
    void deleteToken(String username);

}

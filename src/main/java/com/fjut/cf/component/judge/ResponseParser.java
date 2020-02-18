package com.fjut.cf.component.judge;

import com.alibaba.fastjson.JSONObject;

/**
 * 将返回的response body字符串转化为JsonObject
 *
 * @author axiang [2020/2/16]
 */
public class ResponseParser {
    public JSONObject parserStringToJsonObject(String bodyStr) {
        return JSONObject.parseObject(bodyStr);
    }
}

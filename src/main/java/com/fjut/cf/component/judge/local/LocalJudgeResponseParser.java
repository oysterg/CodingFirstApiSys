package com.fjut.cf.component.judge.local;

import com.alibaba.fastjson.JSONObject;
import com.fjut.cf.component.judge.ResponseParser;
import org.springframework.stereotype.Component;

/**
 * 本地评测机 response body解析类
 * @author axiang [2020/2/16]
 */
@Component
public class LocalJudgeResponseParser extends ResponseParser {
    @Override
    public JSONObject parserStringToJsonObject(String bodyStr) {
        return super.parserStringToJsonObject(bodyStr);
    }
}

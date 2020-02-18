package com.fjut.cf.component.judge.vjudge;

import com.alibaba.fastjson.JSONObject;
import com.fjut.cf.component.judge.ResponseParser;
import org.springframework.stereotype.Component;

/**
 * VJ的Response body解析类
 *
 * @author axiang [2020/2/16]
 */
@Component
public class VirtualJudgeResponseParser extends ResponseParser {
    @Override
    public JSONObject parserStringToJsonObject(String bodyStr) {
        return super.parserStringToJsonObject(bodyStr);
    }


}

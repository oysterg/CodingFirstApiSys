package com.fjut.cf.component.judge.local;

import com.alibaba.fastjson.JSONObject;
import com.fjut.cf.component.judge.OnlineJudgeResponseExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 本地评测机 response body解析类
 * @author axiang [2020/2/16]
 */
@Component
public class LocalJudgeResponseExtractor extends OnlineJudgeResponseExtractor {
    /**
     * 将body解析为json
     *
     * @param response
     * @return
     */
    @Override
    protected JSONObject extractBodyAsJsonObject(ResponseEntity<String> response) {
        return super.extractBodyAsJsonObject(response);
    }

    /**
     * 将body解析为字符串
     *
     * @param response
     * @return
     */
    public String extractBodyAsString(ResponseEntity<String> response) {
        return super.getResponseBody(response);
    }

}

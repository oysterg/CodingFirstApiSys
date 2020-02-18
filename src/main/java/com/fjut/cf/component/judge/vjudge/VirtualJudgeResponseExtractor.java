package com.fjut.cf.component.judge.vjudge;

import com.alibaba.fastjson.JSONObject;
import com.fjut.cf.component.judge.OnlineJudgeResponseExtractor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Virtual Judge 的 Response body提取类
 *
 * @author axiang [2020/2/16]
 */
@Component
public class VirtualJudgeResponseExtractor extends OnlineJudgeResponseExtractor {
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

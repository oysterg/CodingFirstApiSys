package team.fjut.cf.component.judge;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 将返回的response body字符串转化为JsonObject 基类
 *
 * @author axiang [2020/2/16]
 */
@Component
public class OnlineJudgeResponseExtractor {
    /**
     * 提取response body 为JSONObject
     * @param response
     * @return
     */
    protected JSONObject extractBodyAsJsonObject(ResponseEntity<String> response) {
        return JSONObject.parseObject(getResponseBody(response));
    }

    /**
     * 提取response body
     * 如果返回码为200则提取body
     * 否则抛出异常
     *
     * @param response
     * @return
     */
    protected String getResponseBody(ResponseEntity<String> response) throws RuntimeException {
        if (response.getStatusCodeValue() == HttpStatus.SC_OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("请求的网页返回码不正常");
        }
    }

}

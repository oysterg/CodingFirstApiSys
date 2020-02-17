package com.fjut.cf.component.judge.local;

import com.alibaba.fastjson.JSONObject;
import com.fjut.cf.component.judge.local.pojo.LocalJudgeSubmitInfoParams;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 与本地评测机的通讯
 *
 * @author axiang [2019/7/31]
 */
@Component
public class LocalJudgeHttpClient {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LocalJudgeResponseParser localJudgeResponseParser;

    @Value("${cf.config.localJudgePath}")
    private String localJudgePath;

    public JSONObject submitToLocalJudge(LocalJudgeSubmitInfoParams localJudgeSubmitInfoParams){
        String postURL = localJudgePath;
        //入参
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("type", "submit");
        map.add("pid", localJudgeSubmitInfoParams.getPid().toString());
        map.add("rid", localJudgeSubmitInfoParams.getRid().toString());
        map.add("timelimit", localJudgeSubmitInfoParams.getTimeLimit().toString());
        map.add("memorylimit", localJudgeSubmitInfoParams.getMemoryLimit().toString());
        map.add("code", localJudgeSubmitInfoParams.getCode());
        map.add("language", localJudgeSubmitInfoParams.getLanguageId().toString());
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(postURL, request, String.class);
        // 如果返回200状态码
        if (responseEntity.getStatusCodeValue() == HttpStatus.SC_OK) {
            return localJudgeResponseParser.parserStringToJsonObject(responseEntity.getBody());
        } else {
            return null;
        }

    }

    public JSONObject getResultFromLocalJudge(Integer rid){
        String postURL = localJudgePath;
        //入参
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("type", "getResult");
        map.add("rid", rid.toString());
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(postURL, request, String.class);
        // 如果返回200状态码
        if (responseEntity.getStatusCodeValue() == HttpStatus.SC_OK) {
            return localJudgeResponseParser.parserStringToJsonObject(responseEntity.getBody());
        } else {
            return null;
        }

    }
}







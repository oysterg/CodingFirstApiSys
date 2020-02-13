package com.fjut.cf.component.judge.vjudge;

import com.fjut.cf.component.judge.vjudge.pojo.VirtualJudgeRequestProblemParams;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Virtual Judge 通讯工具类
 *
 * @author axiang [2020/2/9]
 */
@Component
public class VirtualJudgeHttpClient {
    private static RestTemplate restTemplate = new RestTemplate();
    /**
     * 题目请求url
     */
    static String requestProblemUrl = "https://vjudge.net/problem/data";

    public static String postProblemList(VirtualJudgeRequestProblemParams params) {
        //入参
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("start", params.getStart());
        map.add("length", params.getLength());
        map.add("OJId", params.getOJId());
        map.add("probNum", params.getProbNum());
        map.add("title", params.getTitle());
        map.add("source", params.getSource());
        map.add("category", params.getCategory());
        map.add("order[0][dir]", "desc");
        // 5 为默认对最后更新时间排序
        map.add("order[0][column]", "5");
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(requestProblemUrl, request, String.class);
        if (responseEntity.getStatusCodeValue() == HttpStatus.SC_OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }

}

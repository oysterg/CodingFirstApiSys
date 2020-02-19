package team.fjut.cf.component.judge.vjudge;

import com.alibaba.fastjson.JSONObject;
import team.fjut.cf.component.judge.OnlineJudgeHttpClient;
import team.fjut.cf.component.judge.vjudge.pojo.RequestProblemHtmlParams;
import team.fjut.cf.component.judge.vjudge.pojo.RequestProblemListParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Virtual Judge 通讯工具类
 *
 * @author axiang [2020/2/9]
 */
@Component
public class VirtualJudgeHttpClient extends OnlineJudgeHttpClient {
    @Autowired
    VirtualJudgeResponseExtractor virtualJudgeResponseParser;

    private HttpHeaders headers = new HttpHeaders();

    /**
     * 请求 题目列表 URL
     */
    private String problemListUrl = "https://vjudge.net/problem/data";

    /**
     * 请求 OJ列表 URL
     */
    private String remoteOJsUrl = "https://vjudge.net/util/remoteOJs";

    /**
     * 请求 题目HTML页面 URL
     */
    private String problemHtmlUrl = "https://vjudge.net/problem/%s";

    /**
     * 初始化类
     */
    public VirtualJudgeHttpClient() {
        // 设置请求头部
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }

    /**
     * 请求题目列表
     *
     * @param params
     * @return
     */
    public JSONObject postProblemList(RequestProblemListParams params) {
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
        ResponseEntity<String> responseEntity = doPost(problemListUrl, request);
        return virtualJudgeResponseParser.extractBodyAsJsonObject(responseEntity);

    }

    /**
     * 请求 OJ 列表
     *
     * @return
     */
    public JSONObject postRemoteOJs() {
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = doPost(remoteOJsUrl, request);
        return virtualJudgeResponseParser.extractBodyAsJsonObject(responseEntity);
    }

    /**
     * 请求 题目HTML页面
     *
     * @param params
     * @return
     */
    public Object getProblemInfo(RequestProblemHtmlParams params) {
        String realUrl = String.format(problemHtmlUrl, params.getOJId() + "-" + params.getProbNum());
        System.out.println(realUrl);
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = doGet(realUrl, request);
        return virtualJudgeResponseParser.extractProbDesAsObject(responseEntity);
    }

}

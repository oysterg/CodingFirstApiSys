package team.fjut.cf.component.judge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * OJ请求工具类基类
 *
 * @author axiang [2020/2/18]
 */
@Component
public class OnlineJudgeHttpClient {
    @Autowired
    RestTemplate restTemplate;

    public OnlineJudgeHttpClient() {

    }

    /**
     * 执行 post 请求
     *
     * @param url
     * @param request
     * @return
     */
    protected ResponseEntity<String> doPost(String url, HttpEntity<MultiValueMap<String, Object>> request) {
        return restTemplate.postForEntity(url, request, String.class);
    }

    /**
     * 执行 post 请求，返回Resource
     *
     * @param url
     * @param request
     * @return
     */
    protected ResponseEntity<Resource> doPostAsResource(String url, HttpEntity<MultiValueMap<String, Object>> request) {
        return restTemplate.postForEntity(url, request, Resource.class);
    }


    /**
     * 执行 get 请求
     *
     * @param url
     * @param request
     * @return
     */
    protected ResponseEntity<String> doGet(String url, HttpEntity<MultiValueMap<String, Object>> request) {
        return restTemplate.getForEntity(url, String.class);
    }
}

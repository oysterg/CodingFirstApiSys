package team.fjut.cf.component.judge.vjudge;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import team.fjut.cf.component.judge.OnlineJudgeHttpClient;
import team.fjut.cf.component.judge.vjudge.pojo.ProblemHtmlParams;
import team.fjut.cf.component.judge.vjudge.pojo.ProblemListParams;
import team.fjut.cf.component.judge.vjudge.pojo.SubmitParams;
import team.fjut.cf.component.judge.vjudge.pojo.VjAccount;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Virtual Judge 通讯工具类
 *
 * @author axiang [2020/2/9]
 */
@Component
public class VirtualJudgeHttpClient extends OnlineJudgeHttpClient {
    @Autowired
    VirtualJudgeResponseExtractor virtualJudgeResponseParser;

    /**
     * 请求头部
     */
    private HttpHeaders headers = new HttpHeaders();

    /**
     * Cookies保存登录状态
     */
    private List<String> cookies = new ArrayList<>();

    /**
     * 请求 题目列表 URL
     */
    @Value("${cf.config.vj.problemListUrl}")
    private String problemListUrl;

    /**
     * 请求 OJ列表 URL
     */
    @Value("${cf.config.vj.remoteOJsUrl}")
    private String remoteOJsUrl;

    /**
     * 请求 题目HTML页面 URL
     */
    @Value("${cf.config.vj.problemHtmlUrl}")
    private String problemHtmlUrl;

    /**
     * 检查用户是否登录 URL
     */
    @Value("${cf.config.vj.checkLoginStatusUrl}")
    private String checkLoginStatusUrl;

    /**
     * 用户登录 URL
     */
    @Value("${cf.config.vj.doLoginUrl}")
    private String doLoginUrl;

    /**
     * 提交评测 URL
     */
    @Value("${cf.config.vj.submitUrl}")
    private String submitUrl;

    /**
     * 验证码URL
     */
    @Value("${cf.config.vj.captchaUrl}")
    private String captchaUrl;

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
    public JSONObject postProblemList(ProblemListParams params) {
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
    public Object getProblemInfo(ProblemHtmlParams params) {
        String realUrl = String.format(problemHtmlUrl, params.getOJId() + "-" + params.getProbNum());
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = doGet(realUrl, request);
        return virtualJudgeResponseParser.extractProbDesAsObject(responseEntity);
    }

    /**
     * 检查登录状态
     *
     * @return
     */
    public String checkIsLogin() {
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = doPost(checkLoginStatusUrl, request);
        return virtualJudgeResponseParser.extractBodyAsString(responseEntity);
    }

    /**
     * 用户登录
     *
     * @return
     */
    public Object userLogin(VjAccount vjAccount) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", vjAccount.getUsername());
        map.add("password", vjAccount.getPassword());
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = doPost(doLoginUrl, request);
        // 获取Cookie并保存到头部中，即可保存登录状态
        cookies.add(responseEntity.getHeaders().get("Set-Cookie").get(0));
        headers.put(HttpHeaders.COOKIE, cookies);
        return virtualJudgeResponseParser.extractBodyAsString(responseEntity);
    }

    /**
     * 提交问题解答
     *
     * @param params
     * @return
     */
    public JSONObject submitProblem(SubmitParams params) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("language", params.getLanguage());
        map.add("share", params.getShare());
        map.add("source", params.getSource());
        map.add("captcha", params.getCaptcha());
        map.add("oj", params.getOj());
        map.add("probNum", params.getProbNum());
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = doPost(submitUrl, request);
        return virtualJudgeResponseParser.extractBodyAsJsonObject(responseEntity);
    }

    /**
     * 获取验证码
     *
     * @return
     */
    public InputStream getCaptcha() throws IOException {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add(String.valueOf(System.currentTimeMillis()) ,null);
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(map, headers);
        ResponseEntity<Resource> responseEntity = doPostAsResource(captchaUrl, request);
        return virtualJudgeResponseParser.extractBodyAsInputStream(responseEntity);
    }
}

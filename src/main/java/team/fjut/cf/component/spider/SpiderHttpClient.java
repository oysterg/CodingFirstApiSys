package team.fjut.cf.component.spider;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫通讯类
 *
 * @author axiang [2020/4/28]
 */
@Component
@Slf4j
public class SpiderHttpClient {
    @Resource
    RestTemplate restTemplate;

    /**
     * 请求头部
     */
    private HttpHeaders headers = new HttpHeaders();

    /**
     * Cookies保存登录状态
     */
    private List<String> cookies = new ArrayList<>();

    @Value("${cf.config.spider.daemonStatusUrl}")
    private String daemonStatusUrl;

    @Value("${cf.config.spider.listProjectsUrl}")
    private String listProjectsUrl;

    /**
     * 初始化设置头部
     */
    public SpiderHttpClient() {
        //入参设置头部

    }

    /**
     * 获取scrapyd服务器运行状态
     *
     * @return
     */
    public JSONObject getDaemonStatus() {
        return restTemplate.getForObject(daemonStatusUrl, JSONObject.class);
    }

    /**
     * 获取scrapyd服务器已部署爬虫项目列表
     *
     * @return
     */
    public JSONObject getListProjects() {
        return restTemplate.getForObject(listProjectsUrl, JSONObject.class);
    }

}

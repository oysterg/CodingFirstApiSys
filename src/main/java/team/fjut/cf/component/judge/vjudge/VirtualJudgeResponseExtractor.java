package team.fjut.cf.component.judge.vjudge;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import team.fjut.cf.component.judge.OnlineJudgeResponseExtractor;
import team.fjut.cf.component.judge.vjudge.pojo.ProblemDescription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Virtual Judge 的 Response body提取类
 *
 * @author axiang [2020/2/16]
 */
@Component
public class VirtualJudgeResponseExtractor extends OnlineJudgeResponseExtractor {
    /**
     * 将body解析为jsonObject
     *
     * @param response
     * @return
     */
    @Override
    protected JSONObject extractBodyAsJsonObject(ResponseEntity<String> response) {
        JSONObject jsonObject = super.extractBodyAsJsonObject(response);
        jsonObject.put("response_time", response.getHeaders().getDate());
        return jsonObject;
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

    /**
     * 提取VJ题目详情页面的HTML中的题目信息
     *
     * @param response
     * @return
     */
    public Object extractProbDesAsObject(ResponseEntity<String> response) {
        String baseVJUrl = "https://vjudge.net";
        ProblemDescription problemDescription = new ProblemDescription();
        String responseBody = super.getResponseBody(response);
        // 解析HTML文件
        Document document = Jsoup.parse(responseBody);
        Elements elements = document.select("div#prob-properties div.container");
        Map<String, String> map = new HashMap<>(2);
        List<String> dts = elements.select("dt").eachText();
        List<String> dds = elements.select("dd").eachText();
        for (int i = 0; i < dds.size(); i++) {
            map.put(dts.get(i), dds.get(i));
        }

        Elements description = document.select("iframe#frame-description");
        String desUrl = baseVJUrl + description.attr("src");

        Elements titles = document.select("div#prob-title");
        String title = titles.select("h2").text();
        String originUrl = baseVJUrl + titles.select("span a").attr("href");

        problemDescription.setProbTitle(title);
        problemDescription.setOriginUrl(originUrl);
        problemDescription.setMemoryLimit(map.get("Memory limit"));
        problemDescription.setTimeLimit(map.get("Time limit"));
        problemDescription.setAuthor(map.get("Author"));
        problemDescription.setOS(map.get("OS"));
        problemDescription.setSource(map.get("Source"));
        problemDescription.setProblemDescriptionUrl(desUrl);
        problemDescription.setVirtualJudgeUrl(originUrl.substring(0, originUrl.length() - 7));
        return problemDescription;
    }

}

package team.fjut.cf.component.judge.local;

import com.alibaba.fastjson.JSONObject;
import team.fjut.cf.component.judge.OnlineJudgeHttpClient;
import team.fjut.cf.component.judge.local.pojo.LocalJudgeSubmitInfoParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 与本地评测机的通讯
 *
 * @author axiang [2019/7/31]
 */
@Component
public class LocalJudgeHttpClient extends OnlineJudgeHttpClient {

    @Autowired
    LocalJudgeResponseExtractor localJudgeResponseParser;

    @Value("${cf.config.local.localJudgePath}")
    private String localJudgePath;

    private HttpHeaders headers = new HttpHeaders();

    /**
     * 初始化设置头部
     */
    public LocalJudgeHttpClient(){
        //入参设置头部
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }

    public JSONObject submitToLocalJudge(LocalJudgeSubmitInfoParams localJudgeSubmitInfoParams){
        String postURL = localJudgePath;
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
        ResponseEntity<String> responseEntity = doPost(postURL, request);
        return localJudgeResponseParser.extractBodyAsJsonObject(responseEntity);
    }

    public JSONObject getResultFromLocalJudge(Integer rid){
        String postURL = localJudgePath;
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("type", "getResult");
        map.add("rid", rid.toString());
        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = doPost(postURL, request);
        return localJudgeResponseParser.extractBodyAsJsonObject(responseEntity);
    }
}







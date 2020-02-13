package com.fjut.cf.component.judge.local;

import com.fjut.cf.component.judge.local.pojo.LocalJudgeSubmitInfoParams;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 与本地评测机的通讯
 *
 * @author axiang [2019/7/31]
 */
@Component
public class LocalJudgeHttpClient {

    @Value("${cf.config.localJudgePath}")
    private String localJudgePath;

    public String submitToLocalJudge(LocalJudgeSubmitInfoParams localJudgeSubmitInfoParams) throws Exception {
        try {
            String postURL = localJudgePath;
            PostMethod postMethod;
            postMethod = new PostMethod(postURL);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
            NameValuePair[] data = new NameValuePair[]{
                    new NameValuePair("type", "submit"),
                    new NameValuePair("pid", localJudgeSubmitInfoParams.getPid().toString()),
                    new NameValuePair("rid", localJudgeSubmitInfoParams.getRid().toString()),
                    new NameValuePair("timelimit", localJudgeSubmitInfoParams.getTimeLimit().toString()),
                    new NameValuePair("memorylimit", localJudgeSubmitInfoParams.getMemoryLimit().toString()),
                    new NameValuePair("code", localJudgeSubmitInfoParams.getCode()),
                    new NameValuePair("language", localJudgeSubmitInfoParams.getLanguageId().toString()),
            };
            postMethod.setRequestBody(data);
            HttpClient httpClient = new HttpClient();
            httpClient.setConnectionTimeout(3000);
            httpClient.setTimeout(3000);
            // 执行POST方法
            int response = httpClient.executeMethod(postMethod);
            String result = postMethod.getResponseBodyAsString();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public String getResultFromLocalJudge(Integer rid) throws Exception {
        try {
            String postURL = localJudgePath;
            PostMethod postMethod;
            postMethod = new PostMethod(postURL);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
            NameValuePair[] data = new NameValuePair[]{
                    new NameValuePair("type", "getResult"),
                    new NameValuePair("rid", rid.toString())
            };
            postMethod.setRequestBody(data);
            HttpClient httpClient = new HttpClient();
            httpClient.setConnectionTimeout(3000);
            httpClient.setTimeout(3000);
            // 执行POST方法
            int response = httpClient.executeMethod(postMethod);
            String result = postMethod.getResponseBodyAsString();
            return result;
        } catch (Exception e) {
            throw e;
        }


    }
}







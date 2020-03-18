package team.fjut.cf.controller.vj;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.interceptor.PrivateRequired;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import team.fjut.cf.component.judge.vjudge.pojo.SubmitParams;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.VjJudgeResult;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.VjJudgeResultService;
import team.fjut.cf.util.JsonFileUtils;
import team.fjut.cf.util.UUIDUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * VJ评测Controller
 *
 * @author axiang [2020/3/16]
 */
@RestController
@CrossOrigin
@RequestMapping("/vj/judgeResult")
public class VjJudgeResultController {

    @Value("${cf.config.file.tempPath}")
    String tempPath;

    @Autowired
    VirtualJudgeHttpClient virtualJudgeHttpClient;

    @Autowired
    VjJudgeResultService vjJudgeResultService;

    @PrivateRequired
    @PostMapping("/submit")
    public ResultJson submitProblem(@RequestParam("oj") String oj,
                                    @RequestParam("probNum") String probNum,
                                    @RequestParam("language") String language,
                                    @RequestParam("source") String source,
                                    @RequestParam("captcha") String captcha,
                                    @RequestParam("username") String username) throws IOException {
        ResultJson resultJson = new ResultJson();
        SubmitParams params = new SubmitParams();
        params.setOj(oj);
        params.setProbNum(probNum);
        params.setLanguage(language);
        params.setShare("0");
        String newSource = source.replaceAll("\\+", "%2B");
        params.setSource(Base64.getEncoder().encodeToString(newSource.getBytes(StandardCharsets.UTF_8)));
        params.setCaptcha(captcha);
        if ("false".equals(virtualJudgeHttpClient.checkIsLogin())) {
            virtualJudgeHttpClient.userLogin(JsonFileUtils.getRandomVJAccount());
        }
        JSONObject jsonObject = virtualJudgeHttpClient.submitProblem(params);
        if (jsonObject.containsKey("runId")) {
            resultJson.setStatus(ResultCode.REQUIRED_SUCCESS);
            VjJudgeResult vjJudgeResult = new VjJudgeResult();
            vjJudgeResult.setOj(oj);
            vjJudgeResult.setProbNum(probNum);
            vjJudgeResult.setUsername(username);
            vjJudgeResult.setCode(source);
            vjJudgeResult.setRunId(Integer.parseInt(jsonObject.get("runId").toString()));
            vjJudgeResultService.insert(vjJudgeResult);
            //    TODO： 异步线程获取结果
        }
        // 需要验证码时，告诉前端需要更多操作
        else if ("true".equals(jsonObject.get("captcha").toString())) {
            InputStream captchaInputStream = virtualJudgeHttpClient.getCaptcha();
            String filename = UUIDUtils.getUUID32() + ".jpg";
            File captchaFile = new File(tempPath + filename);
            FileUtils.copyInputStreamToFile(captchaInputStream, captchaFile);
            resultJson.addInfo("/image/pic/" + filename);
            resultJson.setStatus(ResultCode.MORE_ACTION_NEEDED);
        } else {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
            resultJson.addInfo(jsonObject);
        }
        return resultJson;
    }
}

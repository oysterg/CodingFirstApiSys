package team.fjut.cf.controller.vj;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.config.interceptor.PrivateRequired;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import team.fjut.cf.component.judge.vjudge.pojo.SubmitParams;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.VjJudgeResult;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.pojo.vo.VjJudgeResultVO;
import team.fjut.cf.service.UserBaseInfoService;
import team.fjut.cf.service.ViewVjJudgeResultService;
import team.fjut.cf.service.VjJudgeResultService;
import team.fjut.cf.service.VjUserProblemSolvedService;
import team.fjut.cf.util.JsonFileTool;
import team.fjut.cf.util.UUIDUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * VJ评测Controller
 *
 * @author axiang [2020/3/16]
 */
@RestController
@CrossOrigin
@RequestMapping("/vj/judge_result")
public class VjJudgeResultController {
    @Autowired
    JsonFileTool jsonFileTool;

    @Value("${cf.config.file.tempPath}")
    String tempPath;

    @Autowired
    VirtualJudgeHttpClient virtualJudgeHttpClient;

    @Autowired
    ViewVjJudgeResultService viewVjJudgeResultService;

    @Autowired
    VjJudgeResultService vjJudgeResultService;

    @Autowired
    VjUserProblemSolvedService vjUserProblemSolvedService;

    @Autowired
    UserBaseInfoService userBaseInfoService;

    @PostMapping("/list")
    public ResultJson getResultList(@RequestParam("pageNum") int pageNum,
                                    @RequestParam("pageSize") int pageSize,
                                    @RequestParam(value = "nickname", required = false) String nickname,
                                    @RequestParam(value = "problemId", required = false) String problemId,
                                    @RequestParam(value = "result", required = false) Integer result,
                                    @RequestParam(value = "language", required = false) String language) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        if (StringUtils.isEmpty(nickname)) {
            nickname = null;
        }
        if (StringUtils.isEmpty(problemId)) {
            problemId = null;
        }
        if (StringUtils.isEmpty(result)) {
            result = null;
        }
        if (StringUtils.isEmpty(language)) {
            language = null;
        }
        List<VjJudgeResultVO> pages = viewVjJudgeResultService.pagesByConditions(pageNum, pageSize,
                nickname, problemId, result, language);
        int count = viewVjJudgeResultService.countByConditions(nickname, problemId, result, language);
        resultJson.addInfo(pages);
        resultJson.addInfo(count);
        return resultJson;
    }

    @PostMapping("/info")
    public ResultJson getResultInfo(@RequestParam("id") int id) {
        VjJudgeResult vjJudgeResult = vjJudgeResultService.select(id);
        return new ResultJson(ResultCode.REQUIRED_SUCCESS, "", vjJudgeResult);
    }


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
        // VJ平台上不分享设置为0
        params.setShare("0");
        // 由于URL转码，需要把 “+” 替换成 “%2B”，在VJ上才能被识别
        String newSource = source.replaceAll("\\+", "%2B");
        params.setSource(Base64.getEncoder().encodeToString(newSource.getBytes(StandardCharsets.UTF_8)));
        params.setCaptcha(captcha);
        if ("false".equals(virtualJudgeHttpClient.checkIsLogin())) {
            virtualJudgeHttpClient.userLogin(jsonFileTool.getRandomVJAccount());
        }
        JSONObject jsonObject = virtualJudgeHttpClient.submitProblem(params);
        // 提交成功会返回runId
        if (jsonObject.containsKey("runId")) {
            resultJson.setStatus(ResultCode.REQUIRED_SUCCESS);
            VjJudgeResult vjJudgeResult = new VjJudgeResult();
            vjJudgeResult.setOj(oj);
            vjJudgeResult.setProbNum(probNum);
            vjJudgeResult.setUsername(username);
            vjJudgeResult.setCode(source);
            vjJudgeResult.setSubmitTime(new Date());
            vjJudgeResult.setRunId(Integer.parseInt(jsonObject.get("runId").toString()));
            vjJudgeResultService.insert(vjJudgeResult);
            vjUserProblemSolvedService.ifSubmitSuccess(vjJudgeResult);
            vjJudgeResultService.queryResultFromVJ(vjJudgeResult);
        }
        // 需要验证码时，告诉前端需要更多操作
        else if ("true".equals(jsonObject.get("captcha").toString())) {
            InputStream captchaInputStream = virtualJudgeHttpClient.getCaptcha();
            String filename = UUIDUtils.getUUID32() + ".jpg";
            File captchaFile = new File(tempPath + filename);
            FileUtils.copyInputStreamToFile(captchaInputStream, captchaFile);
            resultJson.addInfo("/image/temp/" + filename);
            resultJson.setStatus(ResultCode.MORE_ACTION_NEEDED);
        } else {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
            resultJson.addInfo(jsonObject);
        }
        return resultJson;
    }
}

package team.fjut.cf.controller.vj;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import team.fjut.cf.config.interceptor.annotation.LoginRequired;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.SystemInfo;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.SystemInfoService;
import team.fjut.cf.util.Enums2ListUtils;
import team.fjut.cf.util.UUIDUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

/**
 * VJudge 工具类相关API
 *
 * @author axiang [2020/3/3]
 */
@RestController
@CrossOrigin
@RequestMapping("/vj/util")
public class VjUtilController {
    @Value("${cf.config.file.tempPath}")
    String tempPath;

    @Resource
    SystemInfoService systemInfoService;

    @Resource
    VirtualJudgeHttpClient virtualJudgeHttpClient;

    /**
     * 获取VJudge的 remoteOjs的json数据
     * 获取后缓存到数据库中
     * 如果数据库中数据落后7天则重新获取
     *
     * @return
     */
    @PostMapping("/ojs")
    public ResultJson getVJRemoteOJs() {
        String nameInDb = "vj_remote_ojs";
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        // 先找一下数据库中是否有缓存这个数据
        SystemInfo systemInfo = systemInfoService.selectOne(nameInDb);
        //如果没有缓存，则去vj站点获取
        if (Objects.isNull(systemInfo)) {
            JSONObject oJs = virtualJudgeHttpClient.postRemoteOJs();
            resultJson.addInfo(oJs);
            systemInfo = new SystemInfo();
            systemInfo.setName(nameInDb);
            systemInfo.setValue(oJs.toJSONString());
            systemInfo.setInsertTime(new Date());
            systemInfoService.insert(systemInfo);
        }
        // 如果已有记录
        else {
            // 如果超过了7天，重新获取并更新数据
            if (System.currentTimeMillis() - systemInfo.getInsertTime().getTime()
                    >= 1000 * 60 * 60 * 24 * 7) {
                JSONObject oJs = virtualJudgeHttpClient.postRemoteOJs();
                resultJson.addInfo(oJs);
                systemInfo.setValue(oJs.toJSONString());
                systemInfo.setInsertTime(new Date());
                systemInfoService.update(systemInfo);
            } else {
                String value = systemInfo.getValue();
                JSONObject jsonObject = JSONObject.parseObject(value);
                resultJson.addInfo(jsonObject);
            }
        }
        return resultJson;
    }

    @LoginRequired
    @PostMapping("/captcha")
    public ResultJson getCaptcha() throws IOException {
        ResultJson resultJson = new ResultJson();
        InputStream captchaInputStream = virtualJudgeHttpClient.getCaptcha();
        String filename = UUIDUtils.getUUID32() + ".jpg";
        File captchaFile = new File(tempPath + filename);
        FileUtils.copyInputStreamToFile(captchaInputStream, captchaFile);
        resultJson.addInfo("/image/temp/" + filename);
        return resultJson;
    }

    @PostMapping("/languageType")
    public ResultJson getVjLanguageType() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(Enums2ListUtils.parseVJLanguageType());
        return resultJson;
    }

    @PostMapping("/statusType")
    public ResultJson getVjStatusType() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(Enums2ListUtils.parseVJStatusType());
        return resultJson;
    }


}

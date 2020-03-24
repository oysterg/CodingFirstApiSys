package team.fjut.cf.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.token.jwt.JwtTokenManager;
import team.fjut.cf.component.token.TokenModel;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.UserCaptcha;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.UserCaptchaService;
import team.fjut.cf.util.Enums2ListUtils;
import team.fjut.cf.util.UUIDUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author axiang [2020/2/13]
 */
@RestController
@CrossOrigin
@RequestMapping("/util")
public class UtilController {
    @Value("${cf.config.file.tempPath}")
    String tempPath;

    @Autowired
    Producer captchaProducer;

    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    UserCaptchaService userCaptchaService;


    @PostMapping(value = "/captcha")
    public ResultJson getCaptcha(HttpServletRequest request) throws IOException {
        ResultJson resultJson = new ResultJson();
        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token)) {
            resultJson.setStatus(ResultCode.RESOURCE_NOT_EXIST);
            return resultJson;
        }
        TokenModel tokenModel = jwtTokenManager.getTokenModel(token);
        Integer isGuest = "Guest".equals(tokenModel.getRole()) ? 1 : 0;
        String text = captchaProducer.createText();
        UserCaptcha userCaptcha = new UserCaptcha();
        userCaptcha.setName(tokenModel.getUsername());
        userCaptcha.setCaptchaValue(text);
        userCaptcha.setIsGuest(isGuest);
        userCaptchaService.updateCaptcha(userCaptcha);

        BufferedImage image = captchaProducer.createImage(text);
        String filename = UUIDUtils.getUUID32() + ".jpg";
        File file = new File(tempPath + filename);
        ImageIO.write(image, "jpg", file);
        resultJson.addInfo("/image/temp/" + filename);
        return resultJson;
    }

    @GetMapping("/serverTime")
    public ResultJson getServerTime() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(new Date());
        return resultJson;
    }

    @GetMapping("/bugTypes")
    public ResultJson getBugTypes() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(Enums2ListUtils.parseBugType());
        return resultJson;
    }

    @GetMapping("/codeLanguage")
    public ResultJson getCodeLanguage() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(Enums2ListUtils.parseCodeLanguage());
        return resultJson;
    }

    @GetMapping("/submitResult")
    public ResultJson getSubmitResult() {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(Enums2ListUtils.parseSubmitResult());
        return resultJson;
    }
}

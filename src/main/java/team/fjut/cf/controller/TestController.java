package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author axiang [2019/10/21]
 */
@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @Autowired
    VirtualJudgeHttpClient virtualJudgeHttpClient;


    @GetMapping(value = "/test", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] testMethod() throws IOException {
        String s = virtualJudgeHttpClient.checkIsLogin();
        System.out.println("============================== " + s);
        InputStream captcha = virtualJudgeHttpClient.getCaptcha();
        byte[] bytes = new byte[captcha.available()];
        int read = captcha.read(bytes, 0, captcha.available());
        return bytes;
    }
}

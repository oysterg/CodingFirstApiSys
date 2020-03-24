package team.fjut.cf.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * captcha验证码配置
 * FIXME: 本captcha插件有漏洞，找到合适的来替换
 * @author axiang [2020/3/18]
 */
@Configuration
public class CaptchaConfig {
    @Bean
    public DefaultKaptcha captchaProducer() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "yes");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        // 图片宽
        properties.setProperty("kaptcha.image.width", "220");
        // 图片高
        properties.setProperty("kaptcha.image.height", "80");
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "60");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "6");
        // 字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;


    }
}

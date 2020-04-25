package team.fjut.cf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team.fjut.cf.config.interceptor.CaptchaRequestInterceptor;
import team.fjut.cf.config.interceptor.LoginRequestInterceptor;
import team.fjut.cf.config.interceptor.PermissionRequestInterceptor;
import team.fjut.cf.config.interceptor.PrivateRequestInterceptor;

import javax.annotation.Resource;

/**
 * MVC控制器配置
 *
 * @author axiang [2019/10/9]
 */
@Configuration
public class ControllerConfiguration implements WebMvcConfigurer {
    /**
     * 是否启用权限拦截器
     */
    @Value("${cf.config.authInterceptor.enable}")
    private boolean enable;

    /**
     * 图片路径
     */
    @Value("${cf.config.file.picPath}")
    private String picPath;

    /**
     * 头像地址
     */
    @Value("${cf.config.file.avatarPath}")
    private String avatarPath;

    /**
     * 临时地址
     */
    @Value("${cf.config.file.tempPath}")
    private String tempPath;

    @Resource
    private CaptchaRequestInterceptor captchaRequestInterceptor;

    @Resource
    private LoginRequestInterceptor loginRequestInterceptor;

    @Resource
    private PrivateRequestInterceptor privateRequestInterceptor;

    @Resource
    private PermissionRequestInterceptor permissionRequestInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (enable) {
            registry.addInterceptor(captchaRequestInterceptor).addPathPatterns("/**");
            registry.addInterceptor(loginRequestInterceptor).addPathPatterns("/**");
            registry.addInterceptor(privateRequestInterceptor).addPathPatterns("/**");
            registry.addInterceptor(permissionRequestInterceptor).addPathPatterns("/**");
        }
    }

    /**
     * 添加资源处理器
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 重定向资源
        registry.addResourceHandler("/image/temp/**").addResourceLocations("file:" + tempPath);
        registry.addResourceHandler("/image/pic/**").addResourceLocations("file:" + picPath);
        registry.addResourceHandler("/image/avatar/**").addResourceLocations("file:" + avatarPath);
    }
}

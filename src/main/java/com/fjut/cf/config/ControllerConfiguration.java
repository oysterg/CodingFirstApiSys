package com.fjut.cf.config;

import com.fjut.cf.component.interceptor.LoginRequestInterceptor;
import com.fjut.cf.component.interceptor.PrivateRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
    @Value("${cf.config.picturePath}")
    private String picturePath;

    @Autowired
    private LoginRequestInterceptor loginRequestInterceptor;

    @Autowired
    private PrivateRequestInterceptor privateRequestInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (enable) {
            registry.addInterceptor(loginRequestInterceptor).addPathPatterns("/**");
            registry.addInterceptor(privateRequestInterceptor).addPathPatterns("/**");
        }
    }

    /**
     * 添加资源处理器
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 重定向资源到图片文件夹,
        // 通过 http://[ip]:[port]/[projectName]/image/[fileName]访问图片
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + picturePath);
    }
}

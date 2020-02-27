package team.fjut.cf.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置 Druid
 *
 * @author axiang [2019/10/9]
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    @Value("${cf.config.druid.name}")
    private String name;

    @Value("${cf.config.druid.pwd}")
    private String pwd;

    /**
     * 配置Druid的监控
     * 配置一个管理后台的servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>(10);
        //账号
        initParams.put("loginUsername", name);
        //密码
        initParams.put("loginPassword", pwd);
        //默认允许所有
        initParams.put("allow", "");
        //不允许的黑名单ip
        initParams.put("deny", "");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置一个监控的filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>(1);
        initParams.put("exclusions", "*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Collections.singletonList("/*"));

        return bean;
    }
}

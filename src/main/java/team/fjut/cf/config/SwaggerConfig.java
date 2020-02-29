package team.fjut.cf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置Swagger2
 *
 * @author axiang [2019/10/9]
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 关闭 swagger
     */
    @Value("${cf.config.swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket api() {
        // 允许开启swagger
        if (enableSwagger) {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("team.fjut.cf.controller"))
                    .paths(PathSelectors.any())
                    .build();
        } else {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .paths(PathSelectors.none())
                    .build();
        }


    }

    private ApiInfo apiInfo() {
        String licenseUrl = "";
        String license = "";
        String termsOfServiceUrl = "";
        String version = "0.1.0";
        String description = "一码当先 | CodingFirst 项目在线接口文档";
        String title = "一码当先 | CodingFirst 项目在线接口文档";
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .termsOfServiceUrl(termsOfServiceUrl)
                .license(license)
                .licenseUrl(licenseUrl)
                .build();
    }

}

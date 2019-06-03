package com.duan.video.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger-ui接口文档配置
 *
 * @author duanjw
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 是否启用swagger
     */
    @Value("${swagger.enable:true}")
    private boolean enableSwagger;

    /**
     * 创建api的设置
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //是否启用
                .enable(enableSwagger)
                .apiInfo(apiInfo())
                .select()
                //swagger要扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.duan.video.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api显示的基本信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("视频搜索接口")
                .description("视频搜索、视频播放")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("localhost:8080")
                .contact(new Contact("视频搜索", "localhost:8080/swagger-ui.html", "554343346@qq.com"))
                .version("1.0")
                .build();
    }

}

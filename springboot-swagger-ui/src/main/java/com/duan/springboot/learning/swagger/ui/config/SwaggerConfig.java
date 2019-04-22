package com.duan.springboot.learning.swagger.ui.config;

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
 *
 * swagger-ui配置
 *
 * 1. 注解：@EnableSwagger2，开启swagger接口文档功能，启动后访问：http://localhost:8080/swagger-ui.html
 * 2. 注解：@Configuration，标记类为配置类，让Spring来加载该类配置
 * 3. 配置swagger的相关信息
 * 4. enableSwagger，可以动态配置配置swagger是否开启（默认为开启）： swagger.enable: true|false。也可以根据不同的环境配置是否开启
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
                .apis(RequestHandlerSelectors.basePackage("com.duan.springboot.learning.swagger.ui.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api显示的基本信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("学生接口")
                .description("使用swagger-ui生成接口文档")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("localhost:8080")
                .contact(new Contact("学生接口","localhost:8080/swagger-ui.html","554343346@qq.com"))
                .version("1.0")
                .build();
    }

}

## swagger-ui
### 简介
1. 是书写API文档的规范且完整框架；
2. 提供描述、生产、消费和可视化RESTful Web Service；
3. 是由庞大工具集合支撑的形式化规范。这个集合涵盖了从终端用户接口、底层代码库到商业API管理的方方面面。

### 引入
在项目pom引入swagger-ui依赖
```xml

<dependencies>
    thymeleaf
    
    <!--swagger-->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>${swagger.version}</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>${swagger.version}</version>
    </dependency>
</dependencies>

```
### 开启
方式1. 最快捷的方式，在项目的Application.java类（springboot启动类）上添加注解`@EnableSwagger2`

方式2. 添加swagger配置类，同时可以自定义swagger的其它配置
```java
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
```
### 常用注解及作用

| 注解   |      位置      |  作用 |
|----------|-------------|------|
| @EnableSwagger2 |  springboot启动类 | 开启swagger-ui |
| @Api | 接口（controller）类上 | 接口类的描述
| @ApiOperation |    接口的方法上   |   接口的方法的描述 |
| @ApiModel | POJO类上 |    POJO类的描述 |
| @ApiModelProperty | POJO属性上 |    属性的描述
| @ToString | POJO类上 | 默认将类名、所有属性（会按照属性定义顺序），用逗号来分割来生成toString。



参考：https://www.cnblogs.com/heyonggang/p/8638374.html

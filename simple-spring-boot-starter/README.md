## 引入
```xml
       <dependencies>
        <!-- @ConfigurationProperties annotation processing (metadata for IDEs)
                 生成spring-configuration-metadata.json类，需要引入此类-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
    </dependencies>
```
## 命名规则
spring-boot-configuration-processor的作用是编译时生成spring-configuration-metadata.json， 此文件主要给IDE使用，用于提示使用。如在intellij idea中，当配置此jar相关配置属性在application.yml， 你可以用ctlr+鼠标左键，IDE会跳转到你配置此属性的类中。


artifactId的命名问题，Spring 官方 Starter通常命名为spring-boot-starter-{name} 如 spring-boot-starter-web。
Spring官方建议非官方Starter命名应遵循{name}-spring-boot-starter的格式。
package com.duan.springboot.learning.vaildator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * springboot web项目
 *
 * 1. springboot 生成http接口
 * 2. restful风格的http接口
 *
 * 注解：@SpringBootApplication，必须，标明为springboot项目
 *
 * @author duanjw
 * @since 2019-04-16
 */

@SpringBootApplication
public class VaildatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(VaildatorApplication.class, args);
    }

}

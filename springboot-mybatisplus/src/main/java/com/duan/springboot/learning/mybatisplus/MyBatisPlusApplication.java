package com.duan.springboot.learning.mybatisplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * mybatisplus 启动类
 *
 * 使用mybatisplus + mysql数据库，初始化表结构和数据在resources/db/
 *
 *
 * @author duanjw
 * @since 2019/04/23
 */
@EnableAsync
@SpringBootApplication
public class MyBatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApplication.class, args);
    }

    @Configuration
    @ComponentScan(lazyInit = true)
    static class LocalConfig {
    }
}

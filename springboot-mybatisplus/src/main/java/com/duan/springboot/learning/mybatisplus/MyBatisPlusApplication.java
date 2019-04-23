package com.duan.springboot.learning.mybatisplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * mybatisplus 启动类，此类无需启动，直接运行junit测试类即可
 *
 * 使用mybatisplus + h2数据库，初始化表结构和数据在resources/db/
 *
 *
 * @author duanjw
 * @data 2019/04/23
 */
@SpringBootApplication
public class MyBatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApplication.class, args);
    }
}

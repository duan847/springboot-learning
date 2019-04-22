package com.duan.springboot.learning.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * redis操作启动类
 *
 * 1. 使用RedisTemplate操作redis
 *
 * @author duanjw
 * @date 2019/04/18
 */
@SpringBootApplication
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}

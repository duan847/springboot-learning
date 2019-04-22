package com.duan.springboot.learning.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * springboot cache启动类
 *
 * 注解：@EnableCaching，开启缓存
 *
 * 1. 使用cache注解操作缓存
 * 2. 使用redis作为缓存容器，适应分布式系统
 * 3. 将查询结果放入缓存，再次查询直接从缓存读取
 * 4. 更新缓存
 * 5. 删除缓存
 *
 * @author duanjw
 */
@EnableCaching
@SpringBootApplication
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

}

package com.duan.springboot.learning.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis启动类
 *
 * 1. 使用mybatis连接mysql数据库
 * 2. 使用pagehelper对mybatis查询分页
 * 3. 注解方式@Transactional使用事务，见UserServiceImplTest
 *
 * @author duanjw
 */
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}

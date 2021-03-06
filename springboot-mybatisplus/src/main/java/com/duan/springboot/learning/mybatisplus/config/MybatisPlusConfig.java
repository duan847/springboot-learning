package com.duan.springboot.learning.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatisplus配置
 *
 * @author duanjw
 */
@Configuration
@MapperScan("com.duan.springboot.learning.mybatisplus.mapper")
public class MybatisPlusConfig {
    /**
     * 1.分页插件
     * 2.多租户
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        System.out.println(1);
        return new PaginationInterceptor().setDialectType("h2");
    }
//
//    @Bean
//    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
//        return new OptimisticLockerInterceptor();
//    }
}

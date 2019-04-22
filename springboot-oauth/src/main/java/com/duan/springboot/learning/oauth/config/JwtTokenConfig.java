package com.duan.springboot.learning.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 *
 * token使用jtw生成
 *
 * 本人见解：如果没有特殊需求，可以不用把jwt放到redis中
 * 什么情况会需要呢？
 * 1、分布式环境下、服务端需要强制jwt失效。登录时把jwt放到redis中，客户端请求到服务端后，首先从redis中找有没有此jwt，如果没有，重新登录
 * @author duanjw
 */
@Configuration
public class JwtTokenConfig{

    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * token生成处理：指定签名
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("internet_plus");
        return accessTokenConverter;
    }
}

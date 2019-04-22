package com.duan.springboot.learning.oauth.config;

import com.duan.springboot.learning.oauth.handler.AuthenticationFailHandler;
import com.duan.springboot.learning.oauth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author duanjw
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationFailHandler authenticationFailHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
////        auth.inMemoryAuthentication()
////                .withUser("user").password("123456").authorities("ROLE_USER");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//
//                .httpBasic()
////                .authenticationEntryPoint(authenticationEntryPoint)
//
//                .and()
//                .authorizeRequests()
//
//                .anyRequest()
//                .authenticated()// 其他 url 需要身份认证
//
//                .and()
//                .formLogin()  //开启登录
////                .successHandler(authenticationSuccessHandler) // 登录成功
//                .failureHandler(authenticationFailHandler) // 登录失败
//                .permitAll()
//
//                .and()
//                .logout()
////                .logoutSuccessHandler(logoutSuccessHandler)
//                .permitAll();
//    }
}

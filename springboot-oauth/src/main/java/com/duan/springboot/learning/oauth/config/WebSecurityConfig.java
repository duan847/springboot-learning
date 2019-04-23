package com.duan.springboot.learning.oauth.config;

import com.duan.springboot.learning.oauth.handler.AuthenticationFailHandler;
import com.duan.springboot.learning.oauth.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
//
//    }
}

package com.duan.springboot.learning.oauth.config;

import com.duan.springboot.learning.oauth.component.sms.SmsCodeSecurityConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


/**
 * 资源服务器配置
 *
 * @author duanjw
 */
@Configuration
@EnableResourceServer
@Slf4j
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Autowired
    private SmsCodeSecurityConfigurer smsCodeSecurityConfigurer;

    /**
     * 配置资源服务器安全属性，如Token的配置，这些是与 AuthorizationServer 授权服务器的配置是匹配的。
     *
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    /**
     * 配置资源的保护
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/mobile/token").permitAll()
                .antMatchers("/**").authenticated()
                .anyRequest().authenticated();
        http.apply(smsCodeSecurityConfigurer);


    }
}

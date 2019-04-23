package com.duan.springboot.learning.oauth.config;

import com.duan.springboot.learning.oauth.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;

/**
 *
 * 授权服务配置
 * /oauth/authorize：验证接口， AuthorizationEndpoint
 * /oauth/token：获取token
 * /oauth/confirm_access：用户授权
 * /oauth/error：认证失败
 * /oauth/check_token：资源服务器用来校验token
 * /oauth/token_key：jwt模式下获取公钥；位于：TokenKeyEndpoint ，通过 JwtAccessTokenConverter 访问key
 *
 * @author duanjw
 */
@Configuration
@EnableAuthorizationServer
@Slf4j
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    /**
     * 在spring5之后，必须配置加密算法
     * @return
     */
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    private UserDetailsServiceImpl userDetailsService;


    /**
     * 配置AuthorizationServer 端点的非安全属性，也就是 token 存储方式、token 配置、用户授权模式等。
     * 默认不需做任何配置，除非使用 密码授权方式, 这时候必须配置 AuthenticationManager。
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                //自定义token生成方式
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager)
                //刷新token后，原先的refresh_token失效
                .reuseRefreshTokens(true).userDetailsService(userDetailsService);
    }

    /**
     * client客户端的信息。包括权限范围、授权方式、客户端权限等配置
     * 授权方式有4种:implicit, client_redentials, password , authorization_code, 其中密码授权方式必须结合 AuthenticationManager 进行配置。
     * 必须至少配置一个客户端。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //客户端ID
                .withClient("clientid")
                .secret(passwordEncoder.encode("secret"))
                //设置验证方式
                .authorizedGrantTypes("password", "refresh_token", "authorization_code")
                .scopes("read", "write")
                ////token过期时间
                .accessTokenValiditySeconds(10000)
                //refresh过期时间
                .refreshTokenValiditySeconds(10000);
    }
//
//    /**
//     * 安全约束，哪些允许访问，哪些不允许访问
//     * @param oauthServer
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer
//                .tokenKeyAccess("isAuthenticated()")
//                .checkTokenAccess("permitAll()")
//                .allowFormAuthenticationForClients();
//    }
}

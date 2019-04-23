

package com.duan.springboot.learning.oauth.component.sms;

import com.duan.springboot.learning.oauth.userdetail.impl.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @date 2018/1/9
 * 手机号登录校验逻辑
 */
@Slf4j
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
//    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        log.info("登录人手机号：{}", smsCodeAuthenticationToken.getPrincipal());
//        UserVO userVo = userService.findUserByMobile((String) smsCodeAuthenticationToken.getPrincipal());
//
//        if (userVo == null) {
//            throw new UsernameNotFoundException("手机号不存在:" + smsCodeAuthenticationToken.getPrincipal());
//        }
//
//        UserDetailsImpl userDetails = buildUserDeatils(userVo);
        UserDetailsImpl userDetails = new UserDetailsImpl();
        ;
        log.info("用户信息：{}", userDetails.isEnabled());
        SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationToken.setDetails(smsCodeAuthenticationToken.getDetails());
        return authenticationToken;
    }

//    private UserDetailsImpl buildUserDeatils(UserVO userVo) {
//        return new UserDetailsImpl(userVo);
//    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

//    public UserService getUserService() {
//        return userService;
//    }

//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
}

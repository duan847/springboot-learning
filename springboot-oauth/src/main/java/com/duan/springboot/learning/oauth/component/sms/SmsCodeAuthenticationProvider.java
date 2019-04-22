

package com.duan.springboot.learning.oauth.component.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @date 2018/1/9
 * 手机号登录校验逻辑
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
//    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
//        UserVO userVo = userService.findUserByMobile((String) smsCodeAuthenticationToken.getPrincipal());
//
//        if (userVo == null) {
//            throw new UsernameNotFoundException("手机号不存在:" + smsCodeAuthenticationToken.getPrincipal());
//        }
//
//        UserDetailsImpl userDetails = buildUserDeatils(userVo);

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

package com.duan.springboot.learning.oauth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * 自定义认证失败的处理器
 *
 * @author duanjw
 */
@Slf4j
public class AuthenticationFailHandler implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        Throwable throwable = e.getCause();
        log.error("oauth异常", e);
        if (throwable instanceof InvalidTokenException) {
            log.info("token过期");
        }
        return new ResponseEntity("token过期", HttpStatus.OK);
    }
}

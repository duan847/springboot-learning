package com.duan.springboot.learning.oauth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义鉴权失败
 * @author duanjw
 */
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        this.returnJson(response, exception);
    }

    /**
     * 直接返回需要返回的 json 数据
     */
    private void returnJson(HttpServletResponse response,
                            AuthenticationException exception) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        response.getWriter().println("用户名或密码错误");
    }

    /**
     * 直接返会错误页面
     */
    private void returnErrorPage(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException exception) throws IOException, ServletException {
        String strUrl = request.getContextPath() + "/loginErrorPath";
        request.getSession().setAttribute("status", 0);
        request.getSession().setAttribute("message", exception.getLocalizedMessage());
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        response.sendRedirect(strUrl);
    }

}

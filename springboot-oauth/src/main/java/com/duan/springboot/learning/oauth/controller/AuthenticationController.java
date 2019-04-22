package com.duan.springboot.learning.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanjw
 */
@RestController
@RequestMapping
public class AuthenticationController {
    /**
     * 登录页面
     * @return ModelAndView
     */
    @GetMapping("/show")
    public String require() {
        return null;
    }

}

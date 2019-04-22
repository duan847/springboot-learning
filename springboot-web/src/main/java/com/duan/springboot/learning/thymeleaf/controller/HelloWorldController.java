package com.duan.springboot.learning.thymeleaf.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * http接口
 * 注解：@RestController，必须，标明为springboot-web项目，方法可以通过http访问，并且所有接口返回值为json。
 *      相当于SpringMVC中@Controller和@ResponseBody
 *
 * @author duanjw
 */
@RestController
public class HelloWorldController {

    /**
     *
     * hello接口
     * 注解：@GetMapping，接口可通过http的get方法访问。http://localhost:8080/hello
     *
     * 其它常用的接口注解：@PostMapping、@DeleteMapping、@PutMapping等，分别表示可通过http的post、delete、put方法访
     * @return Hello World
     */
    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }
}

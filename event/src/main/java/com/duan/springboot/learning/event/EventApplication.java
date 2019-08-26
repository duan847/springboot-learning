package com.duan.springboot.learning.event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 事件驱动（对应设计模式：观察者模式）
 * 涉及到的角色：事件、事件接收者、事件发布者
 * 事件发布者发布事件->事件接收者接收事件
 *
 * @author duanjw
 */
@SpringBootApplication
public class EventApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }
}

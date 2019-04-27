package com.duan.springboot.learning.ampq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MQ消息消费者
 *
 * @author duanjw
 * @data 2019/04/27
 */
@SpringBootApplication
public class AmqpConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmqpConsumerApplication.class, args);
    }
}

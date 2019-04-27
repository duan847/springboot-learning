package com.duan.springboot.learning.amqp.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MQ消息生产者
 *
 * @author duanjw
 * @data 2019/04/27
 */
@SpringBootApplication
public class AmqpProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmqpProducerApplication.class, args);
    }
}

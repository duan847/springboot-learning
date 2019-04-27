package com.duan.springboot.learning.ampq.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 消息队列
 *
 * @author duanjw
 */
@Configuration
public class QueueConfig {

    /**
     * first队列
     * 如果没有此队列，启动时新增此队列
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue("first");
    }
}

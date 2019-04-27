package com.duan.springboot.learning.ampq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 队列消息监听
 * @author duanjw
 */
@Component
@Slf4j
public class ReceiveListener {

    /**
     * 监听first队列消息
     * @param str
     */
    @RabbitListener(queues="first")
    public void firstListener(String str) {
        log.info("first队列收到消息：:{}", str);
    }
}

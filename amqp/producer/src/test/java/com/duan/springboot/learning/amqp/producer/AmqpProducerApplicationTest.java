package com.duan.springboot.learning.amqp.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmqpProducerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class AmqpProducerApplicationTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 向first队列发送消息
     */
    @Test
    public void test01sendToFirst() {
        amqpTemplate.convertAndSend("first", "你好，first队列");
    }
}

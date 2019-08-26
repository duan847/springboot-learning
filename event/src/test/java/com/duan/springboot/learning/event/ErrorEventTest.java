package com.duan.springboot.learning.event;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试事件发布
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class ErrorEventTest {

    @Autowired
    public ErrorEventPublisher errorEventPublisher;

    /**
     * 发布自定义异常事件，A、B服务都会收到
     */
    @Test
    public void test01publishEvent(){
        errorEventPublisher.publishEvent(new ErrorEvent("我炸了"));
    }
}

package com.duan.springboot.learning.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 事件发布者
 * @author duanjw
 */
@Component
public class ErrorEventPublisher implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public void publishEvent(ApplicationEvent applicationEvent) {
        applicationContext.publishEvent(applicationEvent);
    }
}

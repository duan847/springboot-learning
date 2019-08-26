package com.duan.springboot.learning.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * BA服务接收自定义的异常事件
 * @author duanjw
 */
@Component
public class ServiceBListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof ErrorEvent) {
            System.out.println("我是ServiceB，收到ErrorEvent的消息：" + applicationEvent.getSource());
        }
    }
}

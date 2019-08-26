package com.duan.springboot.learning.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author duanjw
 */
public class ErrorEvent extends ApplicationEvent {
    public ErrorEvent(Object source) {
        super(source);
    }

}

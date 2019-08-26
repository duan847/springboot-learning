package com.duan.springboot.learning.yaml.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 短信配置
 * @author duanjw
 */
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    private Map<String, SmsChannel> channels;

    public void setChannels(Map<String, SmsChannel> channels) {
        this.channels = channels;
    }

    public Map<String, SmsChannel> getChannels() {
        return channels;
    }
}

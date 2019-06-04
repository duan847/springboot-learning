package com.duan.springboot.learning.mybatisplus.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义jackson配置
 * @author duanjw
 */
@Configuration
public class JacksonConfig extends ObjectMapper {
    public JacksonConfig() {
        //返回为null的值去除
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //反序列化时，忽略未知属性
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //解决延迟加载的对象
        //this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
    }
}

package com.duan.springboot.learning.yaml.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmsPropertiesTest {



    @Autowired
    private SmsProperties smsProperties;

    /**
     * 测试Map中自定义bean的注入
     */
    @Test
    public void test01MapProperties(){
        SmsChannel aliyun = smsProperties.getChannels().get("aliyun");
        log.info("smsProperties：{}", aliyun.getUrl());
    }
}

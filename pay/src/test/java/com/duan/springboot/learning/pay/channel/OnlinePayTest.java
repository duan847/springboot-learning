package com.duan.springboot.learning.pay.channel;

import com.duan.springboot.learning.pay.util.SpringBeanGroupContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * 在线支付测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OnlinePayTest {

    private OnlinePay onlinePay;

    /**
     * 查看微信余额
     */
    @Test
    public void test01getWeChatBalance() {
        onlinePay = SpringBeanGroupContextUtil.getBean("weChat");
        BigDecimal balance = onlinePay.getBalance();
        assertThat(balance, allOf(notNullValue(), is(BigDecimal.ZERO)));
        log.info("微信余额：{}", balance);

    }

    /**
     * 查看支付宝余额
     */
    @Test
    public void test02getAliPayBalance() {
        onlinePay = SpringBeanGroupContextUtil.getBean("ali");
        BigDecimal balance = onlinePay.getBalance();
        assertThat(balance, allOf(notNullValue(), is(BigDecimal.ONE)));
        log.info("支付宝余额：{}", balance);
    }
}


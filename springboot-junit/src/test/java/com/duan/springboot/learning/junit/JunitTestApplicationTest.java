package com.duan.springboot.learning.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * 基于springboot的junit测试
 *
 * 1. 超时测试
 * 2. 异常测试
 */
@Slf4j
@RunWith(SpringRunner.class)
public class JunitTestApplicationTest {

    /**
     * 超时测试
     * 如果方法的执行时间超出timeout（毫秒。1秒=1000毫秒）的值，则执行失败
     */
    @Test(timeout = 1000)
    public void testTimeout() {
        log.info("success");
    }

    /**
     * 异常测试
     * 如果方法抛出指定异常，则执行成功
     *
     */
    @Test(expected = NullPointerException.class)
    public void testNullException() {
        throw new NullPointerException();
    }
}

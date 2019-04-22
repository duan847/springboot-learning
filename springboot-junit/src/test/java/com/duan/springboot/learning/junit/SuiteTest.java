package com.duan.springboot.learning.junit;

import com.duan.springboot.learning.junit.controller.StudentControllerSpringBootTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 可以将多个测试类一起执行测试
 * 类中不需要写任何代码
 *
 * @RunWith(Suite.class) 指定为套件测试
 * @Suite.SuiteClasses() 需要一起执行的测试类，多个用{A.class, B.class}
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({JunitTestApplicationTest.class, FixMethodOrderTest.class, StudentControllerSpringBootTest.class})
public class SuiteTest {

}

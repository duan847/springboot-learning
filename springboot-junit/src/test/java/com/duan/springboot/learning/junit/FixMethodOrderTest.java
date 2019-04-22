package com.duan.springboot.learning.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

 /**
  *
  * 测试的方法按指方法名顺序执行
  *
  * 注解：@FixMethodOrder，测试方法的执行顺序
  * 1、默认（MethodSorters.DEFAULT）| 方法名hashcode值来决定
  * 2、按方法名（MethodSorters.NAME_ASCENDING）| 方法名称的进行排序
  * 3、JVM（MethodSorters.JVM）| JVM返回的方法名的顺序执行
  *
  * @author duanjw
  */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FixMethodOrderTest {

     /**
      * 第一个执行
      */
    @Test
    public void test01(){
        log.info("第一个执行");
    }

     /**
      * 第二个执行
      */
     @Test
     public void test02(){
         log.info("第二个执行");
     }

     /**
      * 第三个执行
      */
     @Test
     public void test03(){
         log.info("第三个执行");
     }
}

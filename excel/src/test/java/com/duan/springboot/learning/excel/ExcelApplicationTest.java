package com.duan.springboot.learning.excel;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ExcelApplicationTest.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@Slf4j
public class ExcelApplicationTest {

//    @Test
//    public void readExcel(){
//
//    }

    public static void main(String[] args) {
//        for3();
        for1();



    }
    public static List<Demo> for1(){
        Long startTime = System.currentTimeMillis();
        Demo demo;
        List<Demo> demos = new ArrayList<Demo>();
        for(int i=0;i<1000000;i++){
            demo = new Demo();
            demo.setId(i);
            demos.add(demo);
        }
        System.out.println("第一种." + (System.currentTimeMillis() - startTime));
        return demos;
    }

    public static List<Demo> for3(){
        Long startTime = System.currentTimeMillis();
        List<Demo> demos = new ArrayList<Demo>();
        for(int i=0;i<1000000;i++){
            Demo demo = new Demo();
            demo.setId(i);
            demos.add(demo);
        }
        System.out.println("第二种." + (System.currentTimeMillis() - startTime));
        return demos;
    }
}
class Demo{
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
}

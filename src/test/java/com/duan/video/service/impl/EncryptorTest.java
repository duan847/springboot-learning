//package com.duan.video.service.impl;
//
//import com.duan.video.VideoApplication;
//import lombok.extern.slf4j.Slf4j;
//import org.jasypt.encryption.StringEncryptor;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = VideoApplication.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@Slf4j
//public class EncryptorTest {
//    @Autowired
//    StringEncryptor encryptor;
//
//    @Test
//    public void getPass() {
//        String name = encryptor.encrypt("video");
//        String password = encryptor.encrypt("video123");
//        System.out.println(name + "----------------");
//        System.out.println(password + "----------------");
//    }
//}

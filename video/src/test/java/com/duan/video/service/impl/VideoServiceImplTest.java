package com.duan.video.service.impl;

import com.duan.video.VideoApplication;
import com.duan.video.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VideoApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class VideoServiceImplTest {

    @Autowired
    private VideoService videoService;

    @Test
    public void searchByName() {
        videoService.searchByName("复仇者");
    }

    @Test
    public void selectVideoUrlById() {
        log.info("视频地址：{}",videoService.selectVideoUrlById("51717"));
    }
}

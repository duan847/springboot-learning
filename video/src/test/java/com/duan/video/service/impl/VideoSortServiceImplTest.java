//package com.duan.video.service.impl;
//
//import com.duan.video.VideoApplication;
//import com.duan.video.common.Constants;
//import com.duan.video.service.VideoSortService;
//import lombok.extern.slf4j.Slf4j;
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
//public class VideoSortServiceImplTest {
//    @Autowired
//    private VideoSortService videoSortService;
//
//    @Test
//    public void updateHot() {
//        videoSortService.updateByType(Constants.MOVIE_HOT, 1);
//    }
//
//    @Test
//    public void updateTop250() {
//        videoSortService.updateByType(Constants.MOVIE_TOP250, 1);
//    }
//
//    @Test
//    public void updateComing() {
//        videoSortService.updateByType(Constants.MOVIE_COMING, 1);
//    }
//}

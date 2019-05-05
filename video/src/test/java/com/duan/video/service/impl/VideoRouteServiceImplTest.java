//package com.duan.video.service.impl;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.duan.video.VideoApplication;
//import com.duan.video.pojo.entity.VideoRoute;
//import com.duan.video.service.VideoRouteService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = VideoApplication.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@Slf4j
//public class VideoRouteServiceImplTest {
//
//    @Autowired
//    private VideoRouteService videoRouteService;
//    @Test
//    public void selectByVideoIdPage() {
//        IPage<VideoRoute> videoRouteIPage = videoRouteService.selectByVideoIdPage(new Page(1,-1), 1122485424082710530L);
//        List<VideoRoute> records = videoRouteIPage.getRecords();
//        log.info("根据电影id:{}分页查询视频地址:{}", 1L,records);
//
//    }
//}
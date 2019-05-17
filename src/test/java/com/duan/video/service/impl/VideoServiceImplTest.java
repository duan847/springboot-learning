//package com.duan.video.service.impl;
//
//import cn.hutool.core.io.file.FileReader;
//import com.duan.video.VideoApplication;
//import com.duan.video.common.Query;
//import com.duan.video.service.VideoService;
//import lombok.extern.slf4j.Slf4j;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = VideoApplication.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@Slf4j
//public class VideoServiceImplTest {
//
//    @Autowired
//    private VideoService videoService;
//
//    @Test
//    public void searchByName() {
//        log.info("视频地址：{}",videoService.searchByName("下一任"));
//    }
//
//    @Test
//    public void selectVideoUrlById() {
//        log.info("视频地址：{}",videoService.selectVideoUrlById("76551"));
//    }
//
//    @Test
//    public void start() {
//        videoService.start(1,10);
//    }
//
//    @Test
//    public void start1() {
//        FileReader fileReader = new FileReader("error.log");
//        List<String> result = fileReader.readLines();
//        for (int i = 0; i < result.size(); i++) {
//            if((result.get(i)).split("id：").length > 1) {
//                videoService.start(new Integer[]{Integer.parseInt(result.get(i).split("id：")[1])});
//            }
//        }
//    }
//
//    @Test
//    public void start2(){
//        //49564
//        videoService.start(new Integer[]{78422});
//    }
//
//    @Test
//    public void getById() {
//        for (int i = 0; i < 10; i++) {
//            videoService.crawByNo(i,null);
//        }
//    }
//
//
//    @Test
//    public void douban() throws IOException {
//        Document document = Jsoup.connect("https://movie.douban.com/subject/26100958/").get();
//        log.info("豆瓣：{}",document.html());
//    }
//
//    @Test
//    public void startByDoubanId() {
//        videoService.startByDoubanId(1);
//    }
//
//    @Test
//    public void selectHotPage() {
//        Map map = new HashMap<>();
//        map.put("size",10);
//        videoService.selectHotPage(new Query(map));
//    }
//
//    @Test
//    public void deleteAllInfoById() {
//        videoService.deleteAllInfoById(1128324955751100417L);
//    }
//}

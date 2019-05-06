package com.duan.video.service.impl;

import cn.hutool.core.io.file.FileReader;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.video.VideoApplication;
import com.duan.video.pojo.entity.Video;
import com.duan.video.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VideoApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class VideoServiceImplTest {

    @Autowired
    private VideoService videoService;

    @Test
    public void searchByName() {
        log.info("视频地址：{}",videoService.searchByName("下一任"));
    }

    @Test
    public void selectVideoUrlById() {
        log.info("视频地址：{}",videoService.selectVideoUrlById("76551"));
    }

    @Test
    public void start() {
        videoService.start(1,10);
    }

    @Test
    public void start1() {
        FileReader fileReader = new FileReader("error.log");
        List<String> result = fileReader.readLines();
        for (int i = 0; i < result.size(); i++) {
            if((result.get(i)).split("id：").length > 1) {
                videoService.start(new Integer[]{Integer.parseInt(result.get(i).split("id：")[1])});
            }
        }
    }

    @Test
    public void start2(){
        //49564
        videoService.start(new Integer[]{1183});
    }

    @Test
    public void selectByTextPage() {
        IPage<Video> videoIPage = videoService.selectByTextPage(new Page(1, 10), "复仇者联盟");
        List<Video> videoList = videoIPage.getRecords();
        System.out.println(videoList.size());
    }

    @Test
    public void getById() {
        for (int i = 0; i < 10; i++) {
            videoService.crawByNo(i);
        }
    }
}

package com.duan.video.service.impl;

import cn.hutool.core.io.file.FileReader;
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
        videoService.searchByName("复仇者");
    }

    @Test
    public void selectVideoUrlById() {
        log.info("视频地址：{}",videoService.selectVideoUrlById("51717"));
    }

    @Test
    public void start() {
        videoService.start(50723);
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
        videoService.start(new Integer[]{39464,47943,48122,49564,51902,49280,49282,52142,49458,58017,58020});
    }
}

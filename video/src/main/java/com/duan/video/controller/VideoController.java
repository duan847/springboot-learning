package com.duan.video.controller;

import com.duan.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanjw
 */
@RestController
@RequestMapping("video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    /**
     *
     * @return
     */
    @GetMapping("start")
    public String start(){
        return videoService.start();
    }
}

package com.duan.video.controller;

import com.duan.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    @GetMapping("start/{no}")
    public String start(@PathVariable("no")Integer startNo){
        return videoService.start(startNo);
    }

    /**
     * 分页查询
     * @param map
     * @return
     */
    @GetMapping("page")
    public List selectPage(Map map){
        return null;
    }
}

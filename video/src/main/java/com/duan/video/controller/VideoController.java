package com.duan.video.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.video.Query;
import com.duan.video.pojo.vo.VideoDetailVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.video.pojo.entity.Video;
import com.duan.video.pojo.entity.VideoRoute;
import com.duan.video.service.VideoRouteService;
import com.duan.video.service.VideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author duanjw
 */
@RestController
@RequestMapping("video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoRouteService videoRouteService;
//    /**
//     *
//     * @return
//     */
//    @GetMapping("start/{no}")
//    public String start(@PathVariable("no")Integer startNo){
//        return videoService.start(startNo);
//    }

    /**
     * 根据文本分页查询视频
     * @return
     */
    @ApiOperation("根据文本分页查询视频")
    @GetMapping("/text/{text}/page")
    public IPage<Video> selectByTextPage(Page page,@PathVariable String text){
        return videoService.selectByTextPage(page, text);
    }

    /**
     * 根据id分页查询视频播放地址
     * @return
     */
    @ApiOperation("根据id分页查询视频播放地址")
    @GetMapping("/{id}/route/page")
    public IPage<VideoRoute> selectByTextPage(Page page, @PathVariable Long id){
        return videoRouteService.selectByVideoIdPage(page, id);
    }
}

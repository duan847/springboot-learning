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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author duanjw
 */
@Slf4j
@RestController
@RequestMapping("video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoRouteService videoRouteService;
    /**
     *
     * @return
     */
    @GetMapping("begin/{beginNo}/end/{endNo}")
    public String start(@PathVariable("beginNo")Integer beginNo,@PathVariable("endNo")Integer endNo){
        log.info("爬取视频，从编号：{}到：{}", beginNo, endNo);
        if(endNo == null) {
            endNo = beginNo + 1;
        }
        for (int i = beginNo; i < endNo; i++) {
            videoService.crawByNo(i);
        }

        return "爬取视频，从："+beginNo+"到：" + endNo;
    }

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

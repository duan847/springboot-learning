package com.duan.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.mapper.VideoRouteMapper;
import com.duan.video.pojo.entity.VideoRoute;
import com.duan.video.service.VideoRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 视频线路service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class VideoRouteServiceImpl extends ServiceImpl<VideoRouteMapper, VideoRoute> implements VideoRouteService {

    @Autowired
    private VideoRouteMapper videoRouteMapper;

    @Override
    public IPage<VideoRoute> selectByVideoIdPage(Page page, Long videoId) {
        return videoRouteMapper.selectPage(page, new QueryWrapper<VideoRoute>().lambda().eq(VideoRoute::getVideoId, videoId));
    }

    /**
     * 根据视频id删除视频线路
     * @param videoId
     * @return
     */
    @Override
    public boolean deleteByVideoId(Long videoId) {
        videoRouteMapper.delete(new QueryWrapper<VideoRoute>().lambda().eq(VideoRoute::getVideoId, videoId));
        return true;
    }
}

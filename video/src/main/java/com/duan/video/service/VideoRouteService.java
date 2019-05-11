package com.duan.video.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.video.pojo.entity.VideoRoute;

/**
 * 视频线路service接口
 *
 * @author duanjw
 */
public interface VideoRouteService extends IService<VideoRoute> {

    IPage<VideoRoute> selectByVideoIdPage(Page page, Long id);

    /**
     * 根据视频id删除视频线路
     * @param videoId
     * @return
     */
    boolean deleteByVideoId(Long videoId);
}

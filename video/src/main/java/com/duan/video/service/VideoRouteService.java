package com.duan.video.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.video.pojo.entity.Video;
import com.duan.video.pojo.entity.VideoRoute;

/**
 *
 * 学生service接口
 *
 * @author duanjw
 */
public interface VideoRouteService extends IService<VideoRoute> {

    IPage<VideoRoute> selectByVideoIdPage(Page page, Long id);

    boolean deleteByVideoId(Long videoId);
}

package com.duan.video.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.video.pojo.entity.RouteUrl;

/**
 * 视频播放地址service接口
 *
 * @author duanjw
 */
public interface RouteUrlService extends IService<RouteUrl> {

    /**
     * 根据id分页查询视频播放地址
     * @param page
     * @param id
     * @return
     */
    IPage<RouteUrl> selectByVideoIdPage(Page page, Long id);


    /**
     * 更新所有视频时长
     * @return
     */
    Boolean updateAllFilmLength(Integer current, Integer size);
}

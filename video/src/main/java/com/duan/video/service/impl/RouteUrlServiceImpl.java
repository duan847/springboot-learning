package com.duan.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.mapper.RouteUrlMapper;
import com.duan.video.pojo.entity.RouteUrl;
import com.duan.video.service.RouteUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 视频播放地址service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class RouteUrlServiceImpl extends ServiceImpl<RouteUrlMapper, RouteUrl> implements RouteUrlService {

    @Autowired
    private RouteUrlMapper routeUrlMapper;

    /**
     * 根据id分页查询视频播放地址
     * @param page
     * @param id
     * @return
     */
    @Override
    public IPage<RouteUrl> selectByVideoIdPage(Page page, Long id) {
        return routeUrlMapper.selectPage(page, new QueryWrapper<RouteUrl>().lambda().eq(RouteUrl::getVideoId, id).orderByAsc(RouteUrl::getLine,RouteUrl::getName));

    }
}

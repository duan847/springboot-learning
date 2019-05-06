package com.duan.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.mapper.PersonMapper;
import com.duan.video.mapper.RouteUrlMapper;
import com.duan.video.pojo.entity.Person;
import com.duan.video.pojo.entity.RouteUrl;
import com.duan.video.service.PersonService;
import com.duan.video.service.RouteUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 学生service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class RouteUrlServiceImpl extends ServiceImpl<RouteUrlMapper, RouteUrl> implements RouteUrlService {

}

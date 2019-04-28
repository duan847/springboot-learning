package com.duan.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.mapper.VideoMapper;
import com.duan.video.mapper.VideoRouteMapper;
import com.duan.video.pojo.entity.Video;
import com.duan.video.pojo.entity.VideoRoute;
import com.duan.video.pojo.vo.ResponseDataUtil;
import com.duan.video.service.VideoRouteService;
import com.duan.video.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class VideoRouteServiceImpl extends ServiceImpl<VideoRouteMapper, VideoRoute> implements VideoRouteService {

}

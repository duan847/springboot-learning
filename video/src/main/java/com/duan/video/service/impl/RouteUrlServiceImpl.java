package com.duan.video.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.mapper.RouteUrlMapper;
import com.duan.video.pojo.entity.RouteUrl;
import com.duan.video.service.RouteUrlService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

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
     *
     * @param page
     * @param id
     * @return
     */
    @Override
    public IPage<RouteUrl> selectByVideoIdPage(Page page, Long id) {
        return routeUrlMapper.selectPage(page, new QueryWrapper<RouteUrl>().lambda().eq(RouteUrl::getVideoId, id).orderByAsc(RouteUrl::getLine, RouteUrl::getName));

    }

    /**
     * 更新所有视频时长
     *
     * @return
     */
    @Override
    @Async
    public Boolean updateAllFilmLength(Integer current, Integer size) {
        List<RouteUrl> routeUrlList = new ArrayList<>();
        List<RouteUrl> list = super.list(new QueryWrapper<RouteUrl>().last("LIMIT " + current + "," + size));

        list.forEach(item -> {
            try {
                if (item.getUrl().contains(".m3u8")) {
                    routeUrlList.add(item.setFilmLength(getFilmLengthByUrl(item.getUrl())));
                }
            } catch (IOException e) {
                log.error("获取文件时长异常，视频url：{}", item.getUrl());
            }
        });
        //批量更新
        if (routeUrlList.size() > 0) {
                super.updateBatchById(routeUrlList);
        }

        return true;
    }

    public Double getFilmLengthByUrl(String url) throws IOException {
        Document document = Jsoup.connect(url)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .timeout(1000 * 30)
                .referrer(url)
                .userAgent("AppleCoreMedia/1.0.0.18E226 (Macintosh; U; Intel Mac OS X 10_14_4; zh_cn)")
                .header("accept", "*/*")
                .header("Accept-Encoding", "gzip")
                .header("accept-language", "zh-CN")
                .get();
        System.out.println(document.text());
        String[] result = document.text().split(" ");

        String m3u8Url;
        if (result[result.length - 1].indexOf("/") == 0) {
            java.net.URL urlTmp = new java.net.URL(url);
            StringBuilder sb = new StringBuilder();
            sb = sb.append(urlTmp.getProtocol()).append("://").append(urlTmp.getHost());
            m3u8Url = sb.toString() + "/" + result[result.length - 1];
        } else {
            m3u8Url = url.substring(0, url.lastIndexOf("/")) + "/" + result[result.length - 1];
        }
        document = Jsoup.connect(m3u8Url).ignoreContentType(true)
                .ignoreHttpErrors(true)
                .timeout(1000 * 30)

                .userAgent("AppleCoreMedia/1.0.0.18E226 (Macintosh; U; Intel Mac OS X 10_14_4; zh_cn)")
                .header("accept", "*/*")
                .header("accept-encoding", "gzip")
                .header("accept-language", "zh-cn")
                .get();
        List<String> resultFindAll = ReUtil.findAll("#EXTINF:([1-9]\\d*|0)(\\.\\d{1,2})?", document.text(), 0, new ArrayList<String>());
        double sum = 0;
        for (String s : resultFindAll) {
            sum += Double.parseDouble(s.split(":")[1]);
        }
        return Double.parseDouble(NumberUtil.round(sum, 2) + "");
    }

    public static void main(String[] args) throws IOException {

        //https://yingshi.yazyzw.com/20170810/k4lumoH6/index.m3u8
        //https://yingshi.yazyzw.com/ppvod/6607AD2F30C321DD2AB0A63217B253B2.m3u8
        new RouteUrlServiceImpl().getFilmLengthByUrl("https://vip.yingshidaqian.com/20180615/RJml15Zs/index.m3u8");


        //https://fuli.zuida-youku-le.com/20180111/mclAOGiJ/index.m3u8
//        new RouteUrlServiceImpl().getFilmLengthByUrl("https://fuli.zuida-youku-le.com/20180111/mclAOGiJ/index.m3u8");
    }
}

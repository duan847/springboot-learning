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
import org.springframework.transaction.annotation.Transactional;
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
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    public static String BASE_URL = "http://www.kuqiyy.com/";
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoRouteService videoRouteService;


    @Override
    public List<Video> searchByName(String name) {
        ResponseDataUtil<List<Video>> response = restTemplate.getForObject("http://www.kuqiyy.com/index.php/ajax/suggest.html?mid=1&wd=" + name, ResponseDataUtil.class);
        return response.getList();
    }

    public String getDetailById(String id) {
//        try {
//            Document document = Jsoup.connect("http://www.kuqiyy.com/detail/" + id+".html").get();
//            Elements detail = document.select("dl[class=fed-deta-info fed-margin fed-part-rows fed-part-over]");
//            String fengmian = detail.select("dt a").attr("data-original");
//            String pingfen = detail.select("dt span").get(1).text();
//            String remarks = detail.select("dt span").get(2).text();
//            Elements zhuyan = detail.select("dd ul li");
//            log.info("fengmian：{}", fengmian);
//            log.info("pingfen：{}", pingfen);
//            log.info("remarks：{}", remarks);
//            //电影介绍
//            for (Element element : zhuyan) {
//                log.info("{}：{}", element.select("span").text(), element.select("a").text());
//
//            }
//
//            Element boxs = document.select("div[class*=fed-drop-tops]").get(0);
//            Elements xianlu = boxs.select("ul[class=fed-part-rows] li");
//            Elements dizhi= document.select("div[class=fed-drop-boxs fed-drop-btms fed-matp-v] div");
//            for (int i = 0; i < xianlu.size(); i++) {
//
//                String href = xianlu.get(i).select("a").attr("href");
//                log.info("线路名：{}，地址：{}", xianlu.get(i).select("a").text(), href);
//
//                for (Element element : dizhi.get(i).select("li[class=fed-padding fed-col-xs3 fed-col-md2 fed-col-lg1]")) {
//                    log.info("名称：{}，地址：{}", element.select("a").html(), element.select("a").attr("href"));
//
//                     Document videoDocument = Jsoup.connect("http://www.kuqiyy.com/play/" + element.select("a").attr("href")).get();
//                    Elements url = videoDocument.select("iframe[data-play]");
//
//                    return url.attr("data-play");
//                }
//            }
//
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public String selectVideoUrlById(String id) {
        try {
            Document document = Jsoup.connect("http://www.kuqiyy.com/play/" + id + "-1-1.html").get();
            Elements url = document.select("iframe[data-play]");
            return url.attr("data-play");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * 开始爬取
     * @return
     */
    @Override
    @Transactional
    public String start(Integer startNo){
        Video videoNow;
        if(null == startNo){
            startNo = 1;
        }
        for (int j = startNo; j < 90000; j++) {
        try {
        if(j % 100 == 0) {
            videoNow = new Video().selectOne(new QueryWrapper<Video>().eq("no",j));
            if(null != videoNow) {
                log.info("停止运行，当前no：{}", j);
                break;
            }
        }
        String startUrl = BASE_URL + "detail/" + j + ".html";
                Document document = Jsoup.connect(startUrl).get();
        log.info("开始爬取：{}",startUrl);
//                log.info("1==============:{}",document);
                Elements detail = document.select("dl[class=fed-deta-info fed-margin fed-part-rows fed-part-over]");
                String cover = detail.select("dt a").attr("data-original");
                String score = detail.select("dt span").get(1).text();
                String remarks = detail.select("dt span").get(2).text();
                String name = detail.select("dd h1").text();
                Elements zhuyan = detail.select("dd ul li");
                log.info("cover：{}", cover);
                log.info("score：{}", score);
                log.info("remarks：{}", remarks);
                Video video = new Video();
                video.setNo(j).setCover(cover).setScore(new BigDecimal(score)).setRemarks(remarks).setName(name);
                //电影介绍
                for (Element element : zhuyan) {
                    //为空返回
                    if (null == element.text() || "".equals(element.text().trim())) {
                        continue;
                    }
                    String spanText = element.select("span").text();
                    String aText = element.select("a").text();
                    if ("简介：".equals(spanText)) {
                        log.info("{}", element.text());
                        if (element.text().indexOf("：") > -1) {
                            video.setSynopsis(element.text().split("：")[1]);
                        }
                    } else if ("更新：".equals(spanText)) {
                        log.info("{}", element.text());
                        if (element.text().indexOf("：") > -1) {
                            video.setUpdateTime(element.text().split("：")[1]);
                        }
                    } else if ("年份：".equals(spanText)) {
                        video.setYear(aText);
                    } else if ("地区：".equals(spanText)) {
                        video.setAreaName(aText);
                    } else if ("分类：".equals(spanText)) {
                        video.setTypeName(aText);
                    }  else if ("主演：".equals(spanText)) {
                        video.setStaringName(aText);
                    } else if ("导演：".equals(spanText)) {
                        video.setDirectorName(aText);
                    }else {
                        log.info("{}{}", spanText, aText);
                    }
                }

                //新增视频
                video.insert();
                Element boxs = document.select("div[class*=fed-drop-tops]").get(0);
                Elements xianlu = boxs.select("ul[class=fed-part-rows] li");
                Elements dizhi = document.select("div[class=fed-drop-boxs fed-drop-btms fed-matp-v] div");

                List<VideoRoute> videoRouteList = new ArrayList<>();
                for (int i = 0; i < xianlu.size(); i++) {


                    String href = xianlu.get(i).select("a").attr("href");
                    String lineId = href.substring(href.indexOf("-") + 1, href.lastIndexOf("-"));
                    log.info("线路名：{}，地址：{}，id：{}", xianlu.get(i).select("a").text(), href, lineId);

                    for (Element element : dizhi.get(i).select("ul[class=fed-part-rows] li")) {
                        log.info("名称：{}，地址：{}", element.select("a").html(), element.select("a").attr("href"));

                        Document videoDocument = Jsoup.connect(BASE_URL + element.select("a").attr("href")).get();
//                        log.info("2==============:{}",videoDocument);
                        Elements url = videoDocument.select("iframe[data-play]");
                        log.info("视频播放地址：{}", url.attr("data-play"));


                        //视频地址
                        VideoRoute videoRoute = new VideoRoute();
                        videoRoute.setLine(lineId).setName(element.select("a").html()).setUrl(url.attr("data-play")).setVideoId(video.getId());
                        videoRouteList.add(videoRoute);
                    }
                }
                videoRouteService.saveBatch(videoRouteList);


        } catch (IOException e) {
            log.error("异常：{},id：{}",e,j);
        } catch (Exception e) {
            log.error("异常：{},id：{}",e,j);
        }
        }
        return "从：" + startNo + "开始";
    }
}

package com.duan.video.service.impl;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.common.Constants;
import com.duan.video.mapper.VideoMapper;
import com.duan.video.pojo.entity.*;
import com.duan.video.pojo.vo.ResponseDataUtil;
import com.duan.video.service.*;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private PersonService personService;

    @Autowired
    private RouteUrlService routeUrlService;

    @Autowired
    private CrawErrorService crawErrorService;

    @Autowired
    private IncompletionService incompletionService;

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
     *
     * @return
     */
    @Override
    @Transactional
    public String start(Integer startNo, Integer endNo) {
        if (null == startNo) {
            startNo = 1;
        }
        if (null == endNo) {
            endNo = 10000;
        }

        for (int j = startNo; j < endNo; j++) {
            if (j % 100 == 0) {
                Video videoNow = new Video().selectOne(new QueryWrapper<Video>().eq("no", j));
                if (null != videoNow) {
                    log.info("停止运行，当前no：{}", j);
                    break;
                }
            }
            crawByNo(j);
        }
        return "从：" + startNo + "开始";
    }

    /**
     * 开始爬取
     *
     * @return
     */
    @Override
    @Transactional
    public String start(Integer[] startNo) {
        for (int j = 0; j < startNo.length; j++) {
            crawByNo(startNo[j]);
        }
        return "从：" + startNo + "开始";
    }

    @Override
    public IPage<Video> selectByTextPage(Page page, String text) {
        return videoMapper.selectPage(page, new QueryWrapper<Video>().like("name", text));
    }

    /**
     * 根据no更新视频
     *
     * @param no
     * @return
     */
    @Override
    public boolean updateByNo(Integer no) {
        QueryWrapper<Video> noWrapper = new QueryWrapper<Video>().eq("no", no);
        Video video = videoMapper.selectOne(noWrapper);
        if (null != video) {
            videoMapper.delete(noWrapper);
            videoRouteService.deleteByVideoId(video.getId());
            start(new Integer[no]);
            return true;
        }
        return false;
    }

    @Override
    @Async
    public void crawByNo(Integer id) {
        try {
            String startUrl = BASE_URL + "detail/" + id + ".html";
            //获取请求连接
            Document document = Jsoup.connect(startUrl).timeout(60 * 1000).get();
            //请求头设置，特别是cookie设置
            log.info("开始爬取：{}", startUrl);
            Elements detail = document.select("dl[class=fed-deta-info fed-margin fed-part-rows fed-part-over]");
            if (null == detail || detail.html().trim().equals("")) {
                log.error("没有找到资源，id：{}", id);
                crawErrorService.save(new CrawError().setContent("无资源").setCreateTime(new Date()).setVideoNo(id));
                return;
            }
            String cover = detail.select("dt a").attr("data-original");
            String score = detail.select("dt span[class*=fed-list-score]").text();
            score = (score == null || score.trim().equals("")) ? "0" : score;
            String remarks = detail.select("dt span[class*=fed-list-remarks]").text();
            String name = detail.select("dd h1").text();
            Elements zhuyan = detail.select("dd ul li");
            log.info("cover：{}", cover);
            log.info("score：{}", score);
            log.info("remarks：{}", remarks);


            Video video = new Video();
            video.setNo(id).setCover(cover).setScore(new BigDecimal(score)).setRemarks(remarks).setName(name);

            List<Person> staringList = new ArrayList<>();
            List<Person> directorList = new ArrayList<>();
            //电影介绍
            for (Element element : zhuyan) {
                //为空返回
                if (null == element.text() || "".equals(element.text().trim())) {
                    continue;
                }
                String spanText = element.select("span").text();
                Elements aTag = element.select("a");
                String aText = element.select("a").text();
                if ("简介：".equals(spanText)) {
                    log.info("{}", element.text());
                    if (element.text().split("：").length > 1) {
                        video.setSynopsis(element.text().split("：")[1]);
                    }
                } else if ("更新：".equals(spanText)) {
                    log.info("{}", element.text());
                    if (element.text().split("：").length > 1) {
                        video.setUpdateTime(element.text().split("：")[1]);
                    }
                } else if ("年份：".equals(spanText)) {
                    video.setYear(aText);
                } else if ("地区：".equals(spanText)) {
                    video.setAreaName(aText);
                    video.setArea(Constants.dictMap.get(Constants.AREA_PID_KEY + aText));
                } else if ("分类：".equals(spanText)) {
                    video.setTypeName(aText);
                    video.setType(Constants.dictMap.get(Constants.VIDEO_TYPE_PID_KEY + aText));
                } else if ("主演：".equals(spanText)) {
                    aTag.forEach(item -> {
                        staringList.add(new Person().setName(item.text()).setType(Constants.STARING));
                    });
                } else if ("导演：".equals(spanText)) {
                    aTag.forEach(item -> {
                        directorList.add(new Person().setName(item.text()).setType(Constants.DIRECTOR));
                    });
                } else {
                    log.info("{}{}", spanText, aText);
                }
            }

            //新增视频
            video.insert();

            //新增待完结
            //只要包含"更新"关键字，就添加到待完结
            if (null != remarks) {
                Integer haveCount = 0;
                Integer sumCount = 0;
                if (StrUtil.containsAny(remarks, "更新")) {
                    List<String> resultFindAll = ReUtil.findAll("\\d{1,3}", remarks, 0, new ArrayList<String>());

                    int size = resultFindAll.size();
                    for (int i = 0; i < size; i++) {
                        Integer count = Integer.parseInt(resultFindAll.get(i));
                        if (size == 1) {
                            haveCount = count;
                        } else if (resultFindAll.size() == 2) {
                            if (i == 0) {
                                haveCount = count;
                            } else {
                                sumCount = count;
                            }
                            //如果总集数不为0，并且已更新集数大于总集数，两数交换
                            if (sumCount != 0 && haveCount > sumCount) {
                                haveCount = haveCount ^ sumCount;
                                sumCount = haveCount ^ sumCount;
                                haveCount = haveCount ^ sumCount;
                            }
                        }
                    }
                    incompletionService.save(new Incompletion().setUpdateTime(new Date()).setVideoId(video.getId()).setHaveCount(haveCount).setSumCount(sumCount));
                }
            }

            //新增主演&导演
            staringList.addAll(directorList);
            staringList.forEach(item -> item.setVideoId(video.getId()));
            personService.saveBatch(staringList);

            Element boxs = document.select("div[class*=fed-drop-tops]").get(0);
            Elements xianlu = boxs.select("ul[class=fed-part-rows] li");
            Elements dizhi = document.select("div[class=fed-drop-boxs fed-drop-btms fed-matp-v] div");

            List<RouteUrl> routeUrlList = new ArrayList<>();
            for (int i = 0; i < xianlu.size(); i++) {


                String href = xianlu.get(i).select("a").attr("href");
                Integer lineId = Integer.parseInt(href.substring(href.indexOf("-") + 1, href.lastIndexOf("-")));
                log.info("线路名：{}，地址：{}，id：{}", xianlu.get(i).select("a").text(), href, lineId);

                // 新增视频线路
                VideoRoute videoRoute = new VideoRoute().setLine(lineId).setVideoId(video.getId());
                videoRouteService.save(videoRoute);

                for (Element element : dizhi.get(i).select("ul[class=fed-part-rows] li")) {
                    log.info("名称：{}，地址：{}", element.select("a").html(), element.select("a").attr("href"));

                    Document videoDocument = Jsoup.connect(BASE_URL + element.select("a").attr("href")).get();
                    Elements url = videoDocument.select("iframe[data-play]");
                    log.info("视频播放地址：{}", url.attr("data-play"));


                    //新增视频不同线路的url
                    RouteUrl routeUrl = new RouteUrl();
                    routeUrl.setLine(lineId).setName(element.select("a").html()).setUrl(url.attr("data-play")).setVideoId(video.getId());
                    routeUrlList.add(routeUrl);
                }
            }
            routeUrlService.saveBatch(routeUrlList);

        } catch (Exception e) {
            log.error("异常视频编号：{}", id);
            log.error("出现异常：", e);
            crawErrorService.save(new CrawError().setContent(e.toString()).setCreateTime(new Date()).setVideoNo(id));
        }
    }

    public static void main(String[] args) {
        List<String> resultFindAll = ReUtil.findAll("\\d{1,3}", "更新之5集/40", 0, new ArrayList<String>());
        resultFindAll.forEach(item -> System.out.println(item));
    }
}

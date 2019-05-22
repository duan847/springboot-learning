package com.duan.video.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.common.Constants;
import com.duan.video.common.Query;
import com.duan.video.mapper.VideoMapper;
import com.duan.video.pojo.entity.*;
import com.duan.video.pojo.vo.ResponseDataUtil;
import com.duan.video.pojo.vo.VideoDetailVO;
import com.duan.video.service.*;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.duan.video.common.Constants.BASE_URL;
import static com.duan.video.common.Constants.JSOUP_CONNECTION_TIMEOUT;

/**
 * 学生service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {


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

        for (int j = startNo; j <= endNo; j++) {
            if (j % 100 == 0) {
                Video videoNow = new Video().selectOne(new QueryWrapper<Video>().eq("no", j));
                if (null != videoNow) {
                    log.info("停止运行，当前no：{}", j);
                    break;
                }
            }
            crawByNo(j, null);
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
            crawByNo(startNo[j], null);
        }
        return "从：" + startNo + "开始";
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

    /**
     * 分页查询视频-简单信息
     *
     * @param query 分页参数、条件
     * @return
     */
    @Override
    public Page<Video> selectSimplePage(Query query) {
        return query.setRecords(videoMapper.selectSimplePage(query));
    }

    /**
     * 分页查询视频-详细信息
     *
     * @param query 分页参数、条件
     * @return
     */
    @Override
    public Page<VideoDetailVO> selectDetailPage(Query query) {
        return query.setRecords(videoMapper.selectDetailPage(query));
    }

    /**
     * 分页查询热映视频
     *
     * @param query 分页参数、条件
     * @return
     */
    @Override
    public Page<VideoDetailVO> selectHotPage(Query query) {
        query.getParams().put("type", Constants.MOVIE_HOT);
        return query.setRecords(videoMapper.selectSortPage(query));
    }

    /**
     * 根据id查看视频详细
     *
     * @param id
     * @return
     */
    @Override
    public VideoDetailVO getDetailById(Long id) {
        return videoMapper.getDetailById(id);
    }

    /**
     * 根据视频编号多线程爬取视频，并保存到数据库
     *
     * @param no 视频编号
     */
    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void crawByNo(Integer no, Long videoId) {
        try {
            String startUrl = BASE_URL + "detail/" + no + ".html";
            //获取请求连接
            Document document = Jsoup.connect(startUrl).timeout(JSOUP_CONNECTION_TIMEOUT).get();
            //请求头设置，特别是cookie设置
            log.info("开始爬取：{}", startUrl);
            Elements detail = document.select("dl[class=fed-deta-info fed-margin fed-part-rows fed-part-over]");
            if (null == detail || detail.html().trim().equals("")) {
                crawErrorService.save(new CrawError().setContent("无资源").setCreateTime(new Date()).setVideoNo(no));
                return;
            }
            String cover = detail.select("dt a").attr("data-original");
            String score = detail.select("dt span[class*=fed-list-score]").text();
            score = (score == null || score.trim().equals("")) ? "0" : score;
            String remarks = detail.select("dt span[class*=fed-list-remarks]").text();
            String name = detail.select("dd h1").text();
            Elements zhuyan = detail.select("dd ul li");

            Video video = new Video();
            video.setNo(no).setCover(cover).setScore(new BigDecimal(score)).setRemarks(remarks).setName(name);

            List<Person> staringList = new ArrayList<>();
            List<Person> directorList = new ArrayList<>();
            //电影介绍
            for (Element element : zhuyan) {
                //为空返回
                if (null == element.text() || "".equals(element.text().trim())) {
                    continue;
                }
                if (videoId != null) {
                    video.setUpdateTime(DateUtil.date());
                }
                String spanText = element.select("span").text();
                Elements aTag = element.select("a");
                String aText = element.select("a").text();
                if ("简介：".equals(spanText)) {
                    if (element.text().split("：").length > 1) {
                        video.setSynopsis(element.text().split("：")[1]);
                    }
                } else if ("更新：".equals(spanText)) {
                    if (element.text().split("：").length > 1) {
                        video.setUpdateTimeTmp(element.text().split("：")[1]);
                        if (videoId == null) {
                            video.setCreateTime(DateUtil.date());
                        }
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
                    aTag.forEach(item -> staringList.add(new Person().setName(item.text()).setType(Constants.STARING)));
                } else if ("导演：".equals(spanText)) {
                    aTag.forEach(item -> directorList.add(new Person().setName(item.text()).setType(Constants.DIRECTOR)));
                }
            }

            //新增视频
            video.setId(videoId).insertOrUpdate();
            final long newVideoId = video.getId();
            //根据备注新增待完结视频
            saveIncompletion(remarks, newVideoId);

            //新增主演&导演
            staringList.addAll(directorList);
            staringList.forEach(item -> item.setVideoId(newVideoId));
            personService.saveBatch(staringList);

            Element boxs = document.select("div[class*=fed-drop-tops]").get(0);
            Elements lines = boxs.select("ul[class=fed-part-rows] li");
            Elements dizhi = document.select("div[class=fed-drop-boxs fed-drop-btms fed-matp-v] div");

            List<RouteUrl> routeUrlList = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {


                String href = lines.get(i).select("a").attr("href");
                Integer lineId = Integer.parseInt(href.substring(href.indexOf("-") + 1, href.lastIndexOf("-")));

                //新增视频线路
                VideoRoute videoRoute = new VideoRoute().setLine(lineId).setVideoId(newVideoId);
                videoRouteService.save(videoRoute);

                for (Element element : dizhi.get(i).select("ul[class=fed-part-rows] li")) {

                    Document videoDocument = Jsoup.connect(BASE_URL + element.select("a").attr("href")).get();
                    Elements url = videoDocument.select("iframe[data-play]");

                    //新增视频不同线路的url
                    RouteUrl routeUrl = new RouteUrl();
                    routeUrl.setLine(lineId).setName(element.select("a").html()).setUrl(url.attr("data-play")).setVideoId(newVideoId);
                    routeUrlList.add(routeUrl);
                }
            }

            //新增视频播放地址
            routeUrlService.saveBatch(routeUrlList);

        } catch (Exception e) {
            log.error("异常视频编号：{}", no);
            log.error("出现异常：", e);
            crawErrorService.save(new CrawError().setContent(e.toString()).setCreateTime(new Date()).setVideoNo(no));
        }
    }


    /**
     * 根据备注新增待完结视频
     *
     * @param remarks
     * @return
     */
    private void saveIncompletion(String remarks, Long videoId) {
        //新增待完结
        //只要包含"更新"关键字，就添加到待完结
        if (null != remarks) {
            Integer haveCount = 0;
            Integer sumCount = 0;
            if (StrUtil.containsAny(remarks, "更新", "TC", "TS", "HC")) {
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
                incompletionService.save(new Incompletion().setUpdateTime(new Date()).setVideoId(videoId).setHaveCount(haveCount).setSumCount(sumCount));
            }
        }

    }

    @Override
    public void startByDoubanId(Integer id) {
        String startUrl = "http://api.douban.com/v2/movie/subject/" + id + "?apikey=0b2bdeda43b5688921839c8ecb20399b&city=%E5%8C%97%E4%BA%AC&client=&udid=";

        String forObject = restTemplate.getForObject(startUrl, String.class);
        System.out.println(forObject);
    }

    /**
     * 分页查询视频排行榜
     *
     * @param query
     * @return
     */
    @Override
    public IPage<VideoDetailVO> selectTop250Page(Query query) {
        query.getParams().put("type", Constants.MOVIE_TOP250);
        return query.setRecords(videoMapper.selectSortPage(query));
    }

    /**
     * 根据id更新视频所有信息
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateAllInfoById(Long id) {
        Video video = videoMapper.selectOne(new QueryWrapper<Video>().lambda().eq(Video::getId, id));
        if (null != video) {
            Integer no = video.getNo();
            crawErrorService.deleteByVideoNo(no);
            deleteAllInfoById(id);
            crawByNo(no, video.getId());
            return true;
        }
        return false;
    }

    /**
     * 根据id删除视频所有信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteAllInfoById(Long id) {
        videoRouteService.deleteByVideoId(id);
        routeUrlService.deleteByVideoId(id);
        personService.deleteByVideoId(id);
        incompletionService.deleteByVideoId(id);

        return true;
    }

    /**
     * 爬取最新的视频
     * 定时：从0小时开始每3小时执行一次
     *
     * @return
     */
    @Override
    @Scheduled(cron = "0 0 0/2 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public synchronized boolean crawNow() {
        Integer size = 10;
        log.info(Constants.CRAW_NOW_SRART_MSG, DateUtil.now());
        do {
            //从当前视频最大编号+1开始爬取
            Video video = new Video().selectOne(new QueryWrapper<Video>().lambda().last("LIMIT 1").orderByDesc(Video::getNo));
            Integer startNo = video.getNo() + 1;
            String result = start(startNo, video.getNo() + size);
            log.info(result);
            //如果爬取的结果大于一半，再次爬取
            List<Video> videoList = new Video().selectList(new QueryWrapper<Video>().lambda().ge(Video::getNo, startNo));
            if (videoList.size() < size / 2 - 1) {
                log.info(Constants.CRAW_NOW_END_MSG, videoList.size(), size);
                break;
            } else {
                log.info(Constants.CRAW_NOW_RUN_MSG, videoList.size(), size);
            }
        } while (true);
        return true;
    }

    /**
     * 更新待完结的视频
     * 定时：从1小时开始每1小时执行一次
     * 条件：待完结的更新时间小于一个月
     * 待完结的remarks和现在视频的remarks不相等
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "0 0 1/2 * * ?")
    public boolean updateByIncompletion() {
        Integer size = 30;
        Integer current = 0;
        int count = incompletionService.count(new QueryWrapper<Incompletion>().lambda().gt(Incompletion::getUpdateTime, DateUtil.lastMonth()));
        log.info(Constants.UPDATE_INCOMPLETION_START_MSG, count, DateUtil.now());
        do {
            IPage<Incompletion> page = incompletionService.page(new Page<>(current, size),  new QueryWrapper<Incompletion>().lambda().gt(Incompletion::getUpdateTime, DateUtil.lastMonth()));
            List<Incompletion> incompletionList = page.getRecords();
            if (incompletionList.size() == 0) {
                log.info(Constants.UPDATE_INCOMPLETION_END_MSG);
                break;
            }
            current += 1;
            List<Long> videoIds = new ArrayList<>();
            //找出需要更新的待完结视频
            incompletionList.forEach(item -> {
                Long videoId = item.getVideoId();
                if (null != videoId) {
                    videoIds.add(videoId);
                }
            });
            //待完结视频存在于视频中，则更新待完结视频
            List<Video> videoList = videoMapper.selectBatchIds(videoIds);
            if (videoList.size() == 0) {
                log.info(Constants.UPDATE_INCOMPLETION_END_MSG);
                break;
            }
            videoList.forEach(video -> {
                String thisVideoRemarks = video.getRemarks();
                Integer no = video.getNo();
                String newRemarks = getRemarksByNo(no);
                //如果现在视频的remarks和获取到的remarks不一样，更新视频
                if (StrUtil.isNotEmpty(thisVideoRemarks) && newRemarks != null && !StrUtil.equals(thisVideoRemarks, newRemarks)) {
                    Long videoId = video.getId();
                    this.updateAllInfoById(videoId);
                    incompletionService.deleteByVideoId(videoId);
                    log.info(Constants.INCOMPLETION_UPDATE_MSG, no, thisVideoRemarks, newRemarks);
                }
            });

        } while (true);
        return true;
    }

    /**
     * 分页查询热播电视剧
     * @param query
     * @return
     */
    @Override
    public IPage<VideoDetailVO> selectMVHotPage(Query query) {
        query.getParams().put("type", Constants.MV_HOT);
        return query.setRecords(videoMapper.selectSortPage(query));
    }

    /**
     * 根据编号获取视频remarks
     *
     * @param no
     * @return
     */
    public String getRemarksByNo(Integer no) {
        String remarks = null;

        try {
            String startUrl = BASE_URL + "detail/" + no + ".html";
            //获取请求连接
            Document document = Jsoup.connect(startUrl).timeout(JSOUP_CONNECTION_TIMEOUT).get();
            //请求头设置，特别是cookie设置
            Elements detail = document.select("dl[class=fed-deta-info fed-margin fed-part-rows fed-part-over]");
            if (null == detail || detail.html().trim().equals("")) {
                crawErrorService.save(new CrawError().setContent("无资源").setCreateTime(new Date()).setVideoNo(no));
                return null;
            }
            remarks = detail.select("dt span[class*=fed-list-remarks]").text();

        } catch (Exception e) {
            log.error("异常视频编号：{}", no);
            log.error("更新待完结出现异常：", e);
            crawErrorService.save(new CrawError().setContent(e.toString()).setCreateTime(new Date()).setVideoNo(no));
        }
        return remarks;
    }

}

package com.duan.video.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.common.Constants;
import com.duan.video.mapper.VideoSortMapper;
import com.duan.video.pojo.entity.Video;
import com.duan.video.pojo.entity.VideoSort;
import com.duan.video.service.VideoService;
import com.duan.video.service.VideoSortService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 视频排序service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class VideoSortServiceImpl extends ServiceImpl<VideoSortMapper, VideoSort> implements VideoSortService {

    @Autowired
    private VideoService videoService;

    /**
     * 更新不同类型的视频排序
     *
     * @param type
     * @param page
     * @return
     */
    @Override
    public boolean updateByType(Integer type, Integer page){
        String url = null;
        switch (type){
            case Constants.MOVIE_HOT :
                url = "http://api.douban.com/v2/movie/in_theaters";
                break;
            case Constants.MOVIE_TOP250 :
                url = "http://api.douban.com/v2/movie/top250";
                break;
            case Constants.MOVIE_COMING :
                url = "http://api.douban.com/v2/movie/coming_soon";
                break;
        }
        Integer count = 100;
        List<String> list = new ArrayList<>();
        boolean flag = true;
        do{
            Integer start = (page - 1) * count;
            //获取请求连接
            Document document = null;
            try {
                document = Jsoup.connect(url + "?start=" + start + "&count=" + count).ignoreContentType(true)
                        .ignoreHttpErrors(true)
                        .timeout(1000 * 30)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                        .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                        .header("accept-encoding","gzip, deflate, br")
                        .header("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = JSONObject.parseObject(document.text());
            JSONArray jsonArray = jsonObject.getJSONArray("subjects");
            if(jsonArray.size() == 0){
                flag = false;
            }
            jsonArray.forEach(item -> {
                Map map = (Map)item;
                list.add(map.get("title").toString());
            });
            page += 1;
        }while (flag);

        List<VideoSort> videoSortList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<Video> list1 = videoService.list(new QueryWrapper<Video>().lambda().eq(Video::getName, list.get(i)));

            if(list1.size() > 0) {
                Video video = list1.get(0);
                videoSortList.add(new VideoSort().setSort(i).setType(type).setVideoId(video.getId()));
            }
        }
        if(videoSortList.size() > 0) {
            //先删除，再新增
            super.remove(new QueryWrapper<VideoSort>().lambda().eq(VideoSort::getType,type));
        }
        return super.saveBatch(videoSortList);
    }

    /**
     * 更新所有排序
     * @return
     */
    @Override
    public boolean updateAllSort() {
        updateByType(Constants.MOVIE_HOT, 1);
        updateByType(Constants.MOVIE_TOP250, 1);
        updateByType(Constants.MOVIE_COMING, 1);
        return true;
    }

    /**
     * 根据视频id删除视频排序
     * @param videoId
     * @return
     */
    @Override
    public boolean deleteByVideoId(Long videoId) {
        return remove(new QueryWrapper<VideoSort>().lambda().eq(VideoSort::getVideoId, videoId));
    }
}

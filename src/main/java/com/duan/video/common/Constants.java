package com.duan.video.common;

import java.util.Map;

/**
 * @author duanjw
 */
public class Constants {

    public static String BASE_URL = "http://www.kuqiyy.com/";

    /**
     * 主演
     */
    public static final Integer STARING = 126;

    /**
     * 导演
     */
    public static final Integer DIRECTOR = 124;

    /**
     * 视频类型pid
     */
    public static final Integer VIDEO_TYPE_PID = 1;

    /**
     * 视频类型key
     */
    public static final String VIDEO_TYPE_PID_KEY = VIDEO_TYPE_PID + ":";

    /**
     * 地区pid
     */
    public static final Integer AREA_PID_ = 24;

    /**
     * 地区key
     */
    public static final String AREA_PID_KEY = AREA_PID_ + ":";

    /**
     * 字典
     */
    public static Map<String,Integer> dictMap;

    /**
     * jsoup超时时间，1分钟
     */
    public static final Integer JSOUP_CONNECTION_TIMEOUT = 60 * 1000;


    /**
     * 热映电影
     */
    public static final int MOVIE_HOT = 128;

    /**
     * top250
     */
    public static final int MOVIE_TOP250 = 129;

    /**
     * 即将上映
     */
    public static final int MOVIE_COMING = 130;
    /**
     * 热门电视剧
     */
    public static final int MV_HOT = 131;

    /**
     * 最近热门电影
     */
    public static final int MOVIE_RECENT_HOT = 132;
    /**
     * 爬取最新的视频开始的消息
     */
    public static final String CRAW_NOW_SRART_MSG = "===========最新视频开始爬取。爬取时间：{}===========";

    /**
     * 爬取最新的视频结束的消息
     */
    public static final String CRAW_NOW_END_MSG = "最新视频爬取结束，本次爬取数/需要爬取数：{}/{}，不足一半，停止本次爬取，等待下次运行";

    /**
     * 爬取最新的视频继续运行的消息
     */
    public static final String CRAW_NOW_RUN_MSG = "最新视频：爬取数/需要爬取数：{}/{}，超过一半，继续运行";


    /**
     * 更新待完结视频开始的消息
     */
    public static final String UPDATE_INCOMPLETION_START_MSG = "===========待完结视频开始更新，共{}条。更新时间：{}===========";

    /**
     * 更新待完结视频结束的消息
     */
    public static final String UPDATE_INCOMPLETION_END_MSG = "待完结视频更新结束，等待下次执行";

    /**
     * 待完结视频更新完成的消息
     */
    public static final String INCOMPLETION_UPDATE_MSG = "待完结视频更新：编号：{}，视频名：{}，更新前remarks：{}，更新后remarks：{}";
}

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
}

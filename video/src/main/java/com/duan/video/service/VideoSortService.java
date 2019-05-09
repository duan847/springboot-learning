package com.duan.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.video.pojo.entity.VideoSort;

/**
 * 视频排序service接口
 *
 * @author duanjw
 */
public interface VideoSortService extends IService<VideoSort> {
    /**
     * 更新不同类型的视频排序
     *
     * @param type
     * @param page
     * @return
     */
    boolean updateByType(Integer type, Integer page);

    /**
     * 更新所有排序
     * @return
     */
    boolean updateAllSort();
}

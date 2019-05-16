package com.duan.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.video.pojo.entity.Incompletion;

/**
 * 待完结service接口
 *
 * @author duanjw
 */
public interface IncompletionService extends IService<Incompletion> {

    /**
     * 根据视频id删除待完结
     * @param videoId
     * @return
     */
    boolean deleteByVideoId(Long videoId);
}

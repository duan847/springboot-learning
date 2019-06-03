package com.duan.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.video.pojo.entity.CrawError;

/**
 * 爬取异常service接口
 *
 * @author duanjw
 */
public interface CrawErrorService extends IService<CrawError> {

    /**
     * 根据视频编号删除爬取异常
     * @param videoNo
     * @return
     */
    boolean deleteByVideoNo(Integer videoNo);
}

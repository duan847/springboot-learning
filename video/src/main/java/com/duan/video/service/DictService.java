package com.duan.video.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.video.pojo.entity.Dict;

/**
 * 字典service接口
 *
 * @author duanjw
 */
public interface DictService extends IService<Dict> {

    /**
     * 分页查询视频分类
     *
     * @param page
     * @return
     */
    IPage<Dict> selectTypePage(Page page);
}

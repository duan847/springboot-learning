package com.duan.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.common.Constants;
import com.duan.video.common.Query;
import com.duan.video.mapper.DictMapper;
import com.duan.video.pojo.entity.Dict;
import com.duan.video.pojo.entity.RouteUrl;
import com.duan.video.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private DictMapper dictMapper;

    /**
     * 分页查询视频分类
     *
     * @param page
     * @return
     */
    @Override
    public IPage<Dict> selectTypePage(Page page) {
        return selectByPidPage(page, Constants.VIDEO_TYPE_PID);
    }

    /**
     * 根据pid分页查询字典
     * @param page
     * @param pid
     * @return
     */
    private IPage<Dict> selectByPidPage(Page page, Integer pid) {
        return dictMapper.selectPage(page, new QueryWrapper<Dict>().lambda().eq(Dict::getPid, pid).orderByAsc(Dict::getId));

    }
}

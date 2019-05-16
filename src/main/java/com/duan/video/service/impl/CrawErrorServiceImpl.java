package com.duan.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.mapper.CrawErrorMapper;
import com.duan.video.pojo.entity.CrawError;
import com.duan.video.service.CrawErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 爬取异常service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class CrawErrorServiceImpl extends ServiceImpl<CrawErrorMapper, CrawError> implements CrawErrorService {

    /**
     * 根据视频编号删除爬取异常
     * @param videoNo
     * @return
     */
    @Override
    public boolean deleteByVideoNo(Integer videoNo) {
        return remove(new QueryWrapper<CrawError>().lambda().eq(CrawError::getVideoNo, videoNo));
    }
}

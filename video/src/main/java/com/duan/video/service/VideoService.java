package com.duan.video.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.video.Query;
import com.duan.video.pojo.entity.Video;
import com.duan.video.pojo.vo.VideoDetailVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * 学生service接口
 *
 * @author duanjw
 */
public interface VideoService extends IService<Video> {

    List<Video> searchByName(String name);

    String selectVideoUrlById(String id);

    /**
     * 开始爬取
     * @return
     */
    String start(Integer startNo);

    @Transactional
    String start(Integer[] startNo);

    IPage<Video> selectByTextPage(Page page, String text);

    /**
     * 根据no更新视频
     * @param no
     * @return
     */
    boolean updateByNo(Integer no);
}

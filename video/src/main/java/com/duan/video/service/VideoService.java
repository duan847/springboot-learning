package com.duan.video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.video.common.Query;
import com.duan.video.pojo.entity.Video;
import com.duan.video.pojo.vo.VideoDetailVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学生service接口
 *
 * @author duanjw
 */
public interface VideoService extends IService<Video> {

    List<Video> searchByName(String name);

    String selectVideoUrlById(String id);

    /**
     * 开始爬取
     *
     * @return
     */
    String start(Integer startNo, Integer endNo);

    String start(Integer[] startNo);


    /**
     * 根据视频编号多线程爬取视频，并保存到数据库
     *
     * @param no 视频编号
     */
    void crawByNo(Integer no);

    /**
     * 根据no更新视频
     *
     * @param no
     * @return
     */
    boolean updateByNo(Integer no);

    /**
     * 分页查询视频-简单信息
     *
     * @param query 分页参数、条件
     * @return
     */
    Page<Video> selectSimplePage(Query query);

    /**
     * 分页查询视频-详细信息
     *
     * @param query 分页参数、条件
     * @return
     */
    Page<VideoDetailVO> selectDetailPage(Query query);

    /**
     * 根据id查看视频详细
     * @param id
     * @return
     */
    VideoDetailVO getDetailById(Long id);
}

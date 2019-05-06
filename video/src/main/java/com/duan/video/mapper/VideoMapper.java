package com.duan.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duan.video.Query;
import com.duan.video.pojo.entity.Video;
import com.duan.video.pojo.vo.VideoDetailVO;

import java.util.List;
import java.util.Map;

/**
 *
 * 学生mapper
 *
 * @author duanjw
 */
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * @param query
     * @param map
     * @return
     */
    List<VideoDetailVO> selectPage(Query query);
}

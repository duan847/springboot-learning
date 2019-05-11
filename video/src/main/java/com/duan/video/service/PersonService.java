package com.duan.video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.video.pojo.entity.Person;

/**
 * 人员service接口
 *
 * @author duanjw
 */
public interface PersonService extends IService<Person> {

    /**
     * 根据视频id删除人员
     * @param videoId
     * @return
     */
    boolean deleteByVideoId(Long videoId);
}

package com.duan.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.mapper.PersonMapper;
import com.duan.video.pojo.entity.Person;
import com.duan.video.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 人员service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    /**
     * 根据视频id删除人员
     * @param videoId
     * @return
     */
    @Override
    public boolean deleteByVideoId(Long videoId) {
        return remove(new QueryWrapper<Person>().lambda().eq(Person::getVideoId, videoId));
    }
}

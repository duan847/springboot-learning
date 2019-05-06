package com.duan.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.mapper.PersonMapper;
import com.duan.video.mapper.VideoRouteMapper;
import com.duan.video.pojo.entity.Person;
import com.duan.video.pojo.entity.VideoRoute;
import com.duan.video.service.PersonService;
import com.duan.video.service.VideoRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}

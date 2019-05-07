package com.duan.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.mapper.DictMapper;
import com.duan.video.pojo.entity.Dict;
import com.duan.video.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 字典service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

}

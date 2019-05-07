package com.duan.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.video.mapper.IncompletionMapper;
import com.duan.video.pojo.entity.Incompletion;
import com.duan.video.service.IncompletionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 待完结service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class IncompletionServiceImpl extends ServiceImpl<IncompletionMapper, Incompletion> implements IncompletionService {

}

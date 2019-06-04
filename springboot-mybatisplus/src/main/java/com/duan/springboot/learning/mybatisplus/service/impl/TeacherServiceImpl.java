package com.duan.springboot.learning.mybatisplus.service.impl;

import com.duan.springboot.learning.mybatisplus.pojo.entity.Teacher;
import com.duan.springboot.learning.mybatisplus.mapper.TeacherMapper;
import com.duan.springboot.learning.mybatisplus.service.TeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.springboot.learning.mybatisplus.common.Query;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanjw
 * @since 2019-06-04
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    public TeacherMapper teacherMapper;

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    @Override
    public Page<Teacher> selectPage(Query<Teacher> query) {
      return query.setRecords(teacherMapper.selectPage(query));
    }

}

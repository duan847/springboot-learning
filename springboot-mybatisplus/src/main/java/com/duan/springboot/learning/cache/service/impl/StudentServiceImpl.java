package com.duan.springboot.learning.cache.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.springboot.learning.cache.mapper.StudentMapper;
import com.duan.springboot.learning.cache.pojo.entity.Student;
import com.duan.springboot.learning.cache.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 学生service实现
 *
 * @author duanjw
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

}

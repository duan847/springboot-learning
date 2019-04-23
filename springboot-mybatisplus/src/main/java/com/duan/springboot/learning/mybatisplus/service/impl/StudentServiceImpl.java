package com.duan.springboot.learning.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.springboot.learning.mybatisplus.mapper.StudentMapper;
import com.duan.springboot.learning.mybatisplus.pojo.entity.Student;
import com.duan.springboot.learning.mybatisplus.service.StudentService;
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

package com.duan.springboot.learning.cache.service.impl;

import com.duan.springboot.learning.cache.entity.Student;
import com.duan.springboot.learning.cache.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * 学生service实现
 *
 * @author duanjw
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {


    /**
     * 查询所有学生
     *
     * @return 学生list
     */
    @Override
    public List<Student> select() {
        List<Student> studentList = Arrays.asList(new Student(1, "张三", new Date()), new Student(2, "李四", new Date()));
        return studentList;
    }

    /**
     * 根据id查看学生
     * 并放入缓存，默认的key是参数的值
     *
     * @param id
     * @return Student
     */
    @Override
    @Cacheable(cacheNames = "student")
    public Student findById(Integer id) {
        log.info("开始缓存用户，用户id：{}，缓存的key为：student::{}", id, id);
        if (1 == id) {
            return new Student(1, "张三", new Date());
        }
        return new Student();
    }

    /**
     * 根据id修改学生
     * <p>
     * 由于返回的是成功和失败，所以选择把缓存删除，当调用查看再缓存
     *
     * @param student
     * @return true、false
     */
    @Override
    @CacheEvict(cacheNames = "student", key = "#student.id")
    public boolean updateById(Student student) {
        log.info("根据id修改学生：{}", student);
        if (1 == student.getId()) {
            return true;
        }
        return false;
    }

    /**
     * 根据id修改学生并且返回更新后的学生
     * <p>
     * 直接更新缓存中的学生对象为传入的学生对象
     *
     * @param student 更新后的
     * @return student 更新后的
     */
    @Override
    @CachePut(cacheNames = "student", key = "#student.id")
    public Student updateByIdAndReturn(Student student) {
        log.info("根据id修改学生：{}", student);
        return student;
    }

    /**
     * 根据id删除学生
     *
     * @param id
     * @return true、false
     */
    @Override
    @CacheEvict(cacheNames = "student")
    public boolean deleteById(Integer id) {
        log.info("根据id删除学生：{}", id);
        return true;
    }

    /**
     * 删除所有学生
     * <p>
     * 删除所有学生的缓存
     *
     * @return true、false
     */
    @Override
    @CacheEvict(cacheNames = "student", allEntries = true)
    public boolean deleteAll() {
        return true;
    }

}

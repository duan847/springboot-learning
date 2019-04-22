package com.duan.springboot.learning.cache;

import com.duan.springboot.learning.cache.entity.Student;
import com.duan.springboot.learning.cache.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * 学生service实现类测试
 *
 * @author duanjw
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    /**
     * 测试根据id查看学生
     */
    @Test
    public void test01findById() {
        Student student = studentService.findById(1);
        //学生不为空
        assertThat(student, notNullValue());
        //输入日志
        log.info("根据id查看学生：{}", student);
    }

    /**
     * 测试根据id查看学生第二次
     */
    @Test
    public void test02findByIdTwo() {
        Student student = studentService.findById(1);
        //学生不为空
        assertThat(student, notNullValue());
        //输入日志
        log.info("根据id查看学生：{}", student);
    }

    /**
     * 测试根据id修改学生
     */
    @Test
    public void test03updateById() {
        //根据id修改学生返回结果是否为true
        assertThat(studentService.updateById(new Student(1, "李四", new Date())), is(true));
    }

    /**
     * 测试根据id修改学生，并且执行更新缓存
     */
    @Test
    public void test04updateByIdAndResult() {
        //根据id修改学生返回结果是否不为空
        assertThat(studentService.updateByIdAndReturn(new Student(1, "李四", new Date())), notNullValue());
        Student student = studentService.findById(1);
        //学生姓名不为空，并且等于李四
        assertThat(student.getName(), allOf(notNullValue(),is("李四")));
        //输入日志
        log.info("根据id查看学生：{}", student);
    }


    /**
     * 测试根据id删除学生
     */
    @Test
    public void test05deleteById() {
        //根据id删除学生返回结果是否为true
        assertThat(studentService.deleteById(1), is(true));
    }

    /**
     * 测试删除所有学生
     */
    @Test
    public void test6deleteAll() {
        //根据id删除学生返回结果是否为true
        assertThat(studentService.deleteAll(), is(true));
    }

}

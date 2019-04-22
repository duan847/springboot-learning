package com.duan.springboot.learning.cache.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.springboot.learning.cache.pojo.entity.Student;
import com.duan.springboot.learning.cache.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    /**
     * 测试新增学生
     */
    @Test
    public void test01save() {
        //新增学生返回结果是否为true
        Assert.assertThat(studentService.save(new Student("张三", new Date())), is(true));
    }

    /**
     * 测试批量新增学生
     */
    @Test
    public void test02saveBatch() {
        List<Student> studentList = Arrays.asList(new Student("批量1", new Date()), new Student("批量2", new Date()));
        //新增学生返回结果是否为true
        Assert.assertThat(studentService.saveBatch(studentList), is(true));
    }

    /**
     * 测试新增两个学生不开启事务，如果抛出DataIntegrityViolationException异常，测试执行成功
     * 由于name超出数据库字段长度，此时会新增成功第一条，而第二条新增失败
     */
    @Test(expected = DataIntegrityViolationException.class)
    public void test03savetNotTransactional() {
        //两条新增返回结果是否全部为true
        Assert.assertThat(studentService.save(new Student("张三", new Date())), is(true));
        Assert.assertThat(studentService.save(new Student("张三longlonglonglonglonglonglonglonglong", new Date())), is(true));
        log.info("测试新增两个学生不开启事务。由于name超出数据库字段长度，抛出DataIntegrityViolationException异常，此时会新增成功第一条，而第二条新增失败");
    }

    /**
     * 测试新增两个学生并且开启事务，如果抛出DataIntegrityViolationException异常，测试执行成功
     * 由于name超出数据库字段长度，此时两条都不会新增成功
     * <p>
     * 注解：@Transactional，开启事务，
     * 一般用在方法内有多个update类型语句(insert、updateById、delete)，同时需要保持要不全部执行成功，要不全部执行失败
     */
    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void test04savetAndTransactional() {
        //两条新增返回结果是否全部为true
        Assert.assertThat(studentService.save(new Student("张三", new Date())), is(true));
        Assert.assertThat(studentService.save(new Student("张三longlonglonglonglonglonglonglonglong", new Date())), is(true));
        log.info("新增两个学生并且开启事务。由于name超出数据库字段长度，抛出DataIntegrityViolationException异常，此时两条都不会新增成功");

    }

    /**
     * 测试分页查询学生
     */
    @Test
    public void test05selectPage() {
        Map map = new HashMap();
        map.put("pageNum", 1);
        map.put("pageSize", "20");
        map.put("name", "张");
        IPage<Student> studentIPage = studentService.page(new Page<>(1, 5), new QueryWrapper<Student>()
                .lambda().like(Student::getName, "张"));

        //分页查询结果
        List<Student> studentList = studentIPage.getRecords();
        //学生list不为空
        Assert.assertThat(studentList, notNullValue());
        //名字包含张的有3条
        Assert.assertThat(studentIPage.getTotal(), is(3L));
    }

    /**
     * 测试根据id查看学生
     */
    @Test
    public void test06findById() {
        Student student = studentService.getById(24L);
        //学生不为空
        Assert.assertThat(student, notNullValue());
        //输入日志
        log.info("根据id查看学生：{}", student);
    }

    /**
     * 测试根据id修改学生
     */
    @Test
    public void test07updateById() {
        //根据id修改学生返回结果是否为true
        Assert.assertThat(studentService.updateById(new Student(24L, "李四", new Date())), is(true));
    }

    /**
     * 测试根据id批量修改学生
     */
    @Test
    public void test08updateBatchById() {
        List<Student> studentList = Arrays.asList(new Student(23L, "批量修改1", new Date()), new Student(24L, "批量修改2", new Date()));
        //根据id修改学生返回结果是否为true
        Assert.assertThat(studentService.updateBatchById(studentList), is(true));
    }

    /**
     * 测试根据id删除学生
     */
    @Test
    public void test09deleteById() {
        //根据id删除学生返回结果是否为true
        Assert.assertThat(studentService.removeById(24L), is(true));
    }

    /**
     * 测试根据id批量删除学生
     */
    @Test
    public void test10deleteBatchById() {
        //根据id删除学生返回结果是否为true
        Assert.assertThat(studentService.removeByIds(Arrays.asList(22L, 23L)), is(true));
    }
}

package com.duan.springboot.learning.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.springboot.learning.mybatisplus.pojo.entity.Student;
import com.duan.springboot.learning.mybatisplus.service.StudentService;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;


/**
 * 学生接口测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 测试新增学生
     */
    @Test
    public void test01save() {
        //新增学生返回结果是否为true
        Assert.assertThat(studentService.save(new Student("张三", new Date())), is(true));
        log.info("新增学生成功。");
    }

    /**
     * 测试批量新增学生
     */
    @Test
    public void test02saveBatch() {
        List<Student> studentList = Arrays.asList(new Student("批量1", new Date()), new Student("批量2", new Date()));
        //新增学生返回结果是否为true
        Assert.assertThat(studentService.saveBatch(studentList), is(true));
        log.info("批量新增学生。");
    }

    /**
     * 测试新增两个学生不开启事务，如果抛出DataIntegrityViolationException异常，测试执行成功
     * 由于name超出数据库字段长度，此时会新增成功第一条，而第二条新增失败
     */
    @Test(expected = DataIntegrityViolationException.class)
    public void test03savetNotTransactional() {
        log.info("测试新增两个学生不开启事务。由于name超出数据库字段长度，抛出DataIntegrityViolationException异常，此时会新增成功第一条，而第二条新增失败。");
        //两条新增返回结果是否全部为true
        Assert.assertThat(studentService.save(new Student("张三", new Date())), is(true));
        Assert.assertThat(studentService.save(new Student("张三longlonglonglonglonglonglonglonglong", new Date())), is(true));
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
        log.info("新增两个学生并且开启事务。由于name超出数据库字段长度，抛出DataIntegrityViolationException异常，此时两条都不会新增成功");
        //两条新增返回结果是否全部为true
        Assert.assertThat(studentService.save(new Student("张三", new Date())), is(true));
        Assert.assertThat(studentService.save(new Student("张三longlonglonglonglonglonglonglonglong", new Date())), is(true));
    }

    /**
     * 测试分页查询学生
     */
    @Test
    public void test05selectPage() {
        IPage<Student> studentIPage = studentService.page(new Page<>(1, 5), new QueryWrapper<Student>()
                .lambda().like(Student::getName, "张"));

        //分页查询结果
        List<Student> studentList = studentIPage.getRecords();
        //学生list不为空
        Assert.assertThat(studentList, notNullValue());
        //名字包含张的有3条
        Assert.assertThat(studentIPage.getTotal(), is(3L));
        log.info("分页查询学生，并且名字包含'张'的结果：{}", studentIPage);
    }

    /**
     * 测试查询学生
     * 不分页：size传小于0的值
     */
    @Test
    public void test06select() {
        IPage<Student> studentIPage = studentService.page(new Page<>(1, -1), new QueryWrapper<Student>()
                .lambda().like(Student::getName, "张"));

        //分页查询结果
        List<Student> studentList = studentIPage.getRecords();
        //学生list不为空
        Assert.assertThat(studentList, notNullValue());
        //名字包含张的有3条
        Assert.assertThat(studentList.size(), is(3));
        log.info("查询学生，并且名字包含'张'的结果：{}", studentIPage);
    }

    /**
     * 测试根据id查看学生
     */
    @Test
    public void test07findById() {
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
    public void test08updateById() {
        //根据id修改学生返回结果是否为true
        Assert.assertThat(studentService.updateById(new Student(24L, "李四", new Date())), is(true));
        log.info("根据id修改学生成功。");
    }

    /**
     * 测试根据id批量修改学生
     */
    @Test
    public void test09updateBatchById() {
        List<Student> studentList = Arrays.asList(new Student(23L, "批量修改1", new Date()), new Student(24L, "批量修改2", new Date()));
        //根据id修改学生返回结果是否为true
        Assert.assertThat(studentService.updateBatchById(studentList), is(true));
        log.info("根据id批量修改学生成功。");
    }

    /**
     * 测试根据id删除学生
     */
    @Test
    public void test10deleteById() {
        //根据id删除学生返回结果是否为true
        Assert.assertThat(studentService.removeById(24L), is(true));
        log.info("根据id删除学生成功。");
    }

    /**
     * 测试根据id批量删除学生
     */
    @Test
    public void test11deleteBatchById() {
        //根据id删除学生返回结果是否为true
        Assert.assertThat(studentService.removeByIds(Arrays.asList(22L, 23L)), is(true));
        log.info("根据id批量删除学生成功。");
    }

    /**
     * 测试根据id批量删除学生
     */
    @Test
    public void test12insertBatchById() {
        for (int i = 0; i < 5000; i++) {
        }
        int[] ints = jdbcTemplate.batchUpdate("INSERT INTO student (name ) VALUES ('jdbc') ");
        log.info("jdbcTemplate批量新增成功。", ints);
    }

}

package com.duan.springboot.learning.mybatis.service.impl;

import com.duan.springboot.learning.mybatis.pojo.entity.Student;
import com.duan.springboot.learning.mybatis.service.StudentService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
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
import static org.hamcrest.Matchers.*;
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
     * 测试新增学生
     */
    @Test
    public void test01insert() {
        //新增学生返回结果是否为true
        assertThat(studentService.insert(new Student("张三", new Date())), is(true));
    }

    /**
     * 测试批量新增学生
     */
    @Test
    public void test02insertBatch() {
        List<Student> studentList = new ArrayList<>();
        final long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        for (int i = 0; i < 100000; i++) {
            studentList.add(new Student(i,"张" + i,new Date()));
        }
        System.out.println("用时1：" + (System.currentTimeMillis() - startTime));

        //新增学生返回结果是否为true
        assertThat(studentService.insertBatch(studentList), is(true));
        System.out.println("用时2：" + (System.currentTimeMillis() - startTime));

    }

    /**
     * 测试新增两个学生不开启事务，如果抛出DataIntegrityViolationException异常，测试执行成功
     * 由于name超出数据库字段长度，此时会新增成功第一条，而第二条新增失败
     */
    @Test(expected = DataIntegrityViolationException.class)
    public void test03nsertNotTransactional() {
        //两条新增返回结果是否全部为true
        assertThat(studentService.insert(new Student("张三", new Date())), is(true));
        assertThat(studentService.insert(new Student("张三longlonglonglonglonglonglonglonglong", new Date())), is(true));
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
    public void test04insertAndTransactional() {
        //两条新增返回结果是否全部为true
        assertThat(studentService.insert(new Student("张三", new Date())), is(true));
        assertThat(studentService.insert(new Student("张三longlonglonglonglonglonglonglonglong", new Date())), is(true));
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
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10030; i++) {
            list.add(i);
        }
        map.put("array",list);
        //分页查询结果
        PageInfo<Student> studentPageInfo = studentService.selectPage(map);
        List<Student> studentList = studentPageInfo.getList();
        //学生list不为空
        assertThat(studentList, notNullValue());
        //名字包含张的有3条
        assertThat(studentPageInfo.getTotal(), is(3L));
    }

    /**
     * 测试根据id查看学生
     */
    @Test
    public void test06findById() {
        Student student = studentService.findById(26);
        //学生不为空
        assertThat(student, notNullValue());
        //输入日志
        log.info("根据id查看学生：{}", student);
    }

    /**
     * 测试根据id修改学生
     */
    @Test
    public void test07updateById() {
        //根据id修改学生返回结果是否为true
        assertThat(studentService.updateById(new Student(26, "李四", new Date())), is(true));
    }

    /**
     * 测试根据id批量修改学生
     */
    @Test
    public void test08updateBatchById() {
        List<Student> studentList = new ArrayList<>();
        final long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        for (int i = 0; i < 100000; i++) {
            studentList.add(new Student(i,"张" + i,new Date()));
        }
        System.out.println("用时1：" + (System.currentTimeMillis() - startTime));

//        List<Student> studentList = Arrays.asList(new Student(27, "批量修改1", new Date()), new Student(28, "批量修改2", new Date()));
        //根据id修改学生返回结果是否为true
        assertThat(studentService.updateBatchById(studentList), is(false));
        System.out.println("用时2：" + (System.currentTimeMillis() - startTime));

    }

    /**
     * 测试根据id删除学生
     */
    @Test
    public void test09deleteById() {
        //根据id删除学生返回结果是否为true
        assertThat(studentService.deleteById(26), is(true));
    }

    /**
     * 测试根据id批量删除学生
     */
    @Test
    public void test10deleteBatchById() {
        Integer[] ids = new Integer[]{27, 28};
        //根据id删除学生返回结果是否为true
        assertThat(studentService.deleteBatchById(ids), is(true));
    }
}

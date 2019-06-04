package com.duan.springboot.learning.vaildator.controller;

import com.duan.springboot.learning.common.R;
import com.duan.springboot.learning.vaildator.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;


/**
 *
 * 学生接口测试
 * 1. 使用TestRestTemplate测试http接口
 *
 * @author duanjw
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 测试新增学生
     * @throws Exception
     */
    @Test
    public void test01insert() {
        Student student = new Student(25L,"张三",new Date(),"554343346@qq.com");
        ResponseEntity<R> result = restTemplate.postForEntity("/student", student, R.class);
        R body = result.getBody();
        Integer code =body.getCode();
        log.info("新增学生，参数正确，返回：{}", body);
        //新增老师返回结果是否为0
        assertThat(code, is(0));
    }

    /**
     * 测试新增学生，但是参数错误
     * @throws Exception
     */
    @Test
    public void test02insertIllegalArgument() {
        Student student = new Student(25L,"张三", new Date(),"1@qq.com");
        student.setAge(-1);
        ResponseEntity<R> result = restTemplate.postForEntity("/student", student, R.class);
        R body = result.getBody();
        Integer code =body.getCode();
        log.info("新增学生，参数异常，返回：{}", body);
        //新增老师返回结果是否为0
        assertThat(code, is(1));
    }

    /**
     * 测试查询所有学生
     * @throws Exception
     */
    @Test
    public void select() {
        ParameterizedTypeReference<List<Student>> type = new ParameterizedTypeReference<List<Student>>() {};
        List<Student> studentList = restTemplate.exchange("/student/list", HttpMethod.GET, null, type).getBody();
        //学生list不为空
        assertThat(studentList, notNullValue());
        log.info("查询所有学生：{}", studentList);
    }

    /**
     * 测试根据id查看学生
     * @throws Exception
     */
    @Test
    public void findById() {
        //请求地址、返回对象的类型、url的变量按顺序赋值
        ResponseEntity<Student> result = restTemplate.getForEntity("/student/{id}", Student.class, "1");
        Student student = result.getBody();
        //学生不为空
        assertThat(student, notNullValue());
        log.info("根据id查看学生：{}" + student);
    }

    /**
     * 测试根据id修改学生
     * @throws Exception
     */
    @Test
    public void updateById() {
        Student student = new Student(1L,"李四",new Date(),"554343346@qq.com");
        restTemplate.put("/student", student);
    }

    /**
     * 测试根据id删除学生
     * @throws Exception
     */
    @Test
    public void delete() {
        restTemplate.delete("/student/{id}",1);
    }

}

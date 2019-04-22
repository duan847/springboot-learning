package com.duan.springboot.learning.junit.controller;

import com.duan.springboot.learning.junit.pojo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
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
@Slf4j
public class StudentControllerSpringBootTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 测试新增学生
     * @throws Exception
     */
    @Test
    public void insert() {
        Student student = new Student(1,"张三",new Date());
        ResponseEntity<Boolean> result = restTemplate.postForEntity("/student", student, Boolean.class);
        Boolean flag = result.getBody();
        //新增学生返回结果是否为true
        assertThat(flag, is(true));
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
        Student student = new Student(1,"李四",new Date());
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

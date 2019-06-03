package com.duan.springboot.learning.mybatisplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.duan.springboot.learning.mybatisplus.pojo.entity.Teacher;
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

import java.beans.Transient;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;


/**
 *
 * 老师接口测试
 * 1. 使用TestRestTemplate测试http接口
 *
 * @author duanjw
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class TeacherControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 测试新增老师
     * @throws Exception
     */
    @Test
    public void test01insert() {
        Teacher teacher = new Teacher(25L,"仓老师",new Date());
        ResponseEntity<R> result = restTemplate.postForEntity("/teacher", teacher, R.class);
        Object flag = result.getBody().getData();
        //新增老师返回结果是否为true
        assertThat(flag, is(true));
    }
    /**
     * 测试根据id查看老师
     * @throws Exception
     */
    @Test
    public void test02findById() {
        ParameterizedTypeReference<R<Teacher>> type = new ParameterizedTypeReference<R<Teacher>>() {};
        //请求地址、返回对象的类型、url的变量按顺序赋值
        Teacher teacher = restTemplate.exchange("/teacher/{id}", HttpMethod.GET, null, type, 25L).getBody().getData();
        //老师姓名不为空，并且等于仓老师
        assertThat(teacher.getName(), allOf(notNullValue(),equalTo("仓老师")));
        log.info("根据id查看老师：{}" + teacher);
    }

    /**
     * 测试分页查询老师
     * @throws Exception
     */
    @Test
    public void test03selectPage() {
        ParameterizedTypeReference<IPage<Teacher>> type = new ParameterizedTypeReference<IPage<Teacher>>() {};
        List<Teacher> teacherList = restTemplate.exchange("/teacher/page", HttpMethod.GET, null, type).getBody().getRecords();
        //老师list不为空
        assertThat(teacherList, notNullValue());
        log.info("查询所有老师：{}", teacherList);
    }

    /**
     * 测试根据id修改老师
     * @throws Exception
     */
    @Test
    public void test04updateById() {
        Teacher teacher = new Teacher(25L,"小老师",new Date());
        restTemplate.put("/teacher", teacher);
    }

    /**
     * 测试根据id删除老师
     * @throws Exception
     */
    @Test
    public void test05delete() {
        restTemplate.delete("/student/{id}",25L);
    }

}

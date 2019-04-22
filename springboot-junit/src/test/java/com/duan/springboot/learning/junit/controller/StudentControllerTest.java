package com.duan.springboot.learning.junit.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 学生接口接口测试
 *
 * @deprecated 此web测试是基于springMVC的测试方法，代码不简洁
 * @see StudentControllerSpringBootTest
 */
@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * 测试新增学生
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
        String student = "{\"id\" : \"1\", \"name\" : \"张三\", \"updateTime\" : \""+System.currentTimeMillis()+"\"}";
        String responseString = mvc.perform(
                MockMvcRequestBuilders.post("/student")
                        .accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON).
                        content(student.getBytes()))
                .andExpect(status().isOk()) // 期待返回状态吗码200
                .andDo(print()) // 打印返回的 http response 信息
                .andReturn().getResponse().getContentAsString();
        System.out.println("测试'新增学生'的返回结果：" + responseString);
    }

    /**
     * 测试查询所有学生
     * @throws Exception
     */
    @Test
    public void select() throws Exception {
        String responseString = mvc.perform(MockMvcRequestBuilders.get("/student/list"))
                .andExpect(status().isOk()) // 期待返回状态吗码200
                .andDo(print()) // 打印返回的 http response 信息
                .andReturn().getResponse().getContentAsString();
        System.out.println("测试'查询所有学生'的返回结果：" + responseString);
    }

    /**
     * 测试根据id查看学生
     * @throws Exception
     */
    @Test
    public void find() throws Exception {
        String responseString = mvc.perform(
                MockMvcRequestBuilders.get("/student/{id}",1))
                .andExpect(status().isOk()) // 期待返回状态吗码200
                .andDo(print()) // 打印返回的 http response 信息
                .andReturn().getResponse().getContentAsString();
        System.out.println("测试'根据id查看学生'的返回结果：" + responseString);
    }

    /**
     * 测试根据id修改学生
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        String student = "{\"id\" : \"1\", \"name\" : \"李四\", \"updateTime\" : \"" + System.currentTimeMillis() + "\"}";
        String responseString = mvc.perform(
                MockMvcRequestBuilders.put("/student")
                        .accept(MediaType.APPLICATION_JSON).
                        contentType(MediaType.APPLICATION_JSON).
                        content(student.getBytes()))
                .andExpect(status().isOk()) // 期待返回状态吗码200
                .andDo(print()) // 打印返回的 http response 信息
                .andReturn().getResponse().getContentAsString();
        System.out.println("测试'根据id修改学生'的返回结果：" + responseString);
    }

    /**
     * 测试根据id删除学生
     * @throws Exception
     */
    @Test
    public void delete() throws Exception {
        String responseString = mvc.perform(
                MockMvcRequestBuilders.delete("/student/{id}",1))
                .andExpect(status().isOk()) // 期待返回状态吗码200
                .andDo(print()) // 打印返回的 http response 信息
                .andReturn().getResponse().getContentAsString();
        System.out.println("测试'根据id删除学生'的返回结果：" + responseString);
    }

}

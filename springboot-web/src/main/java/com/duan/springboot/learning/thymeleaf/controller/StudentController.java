package com.duan.springboot.learning.thymeleaf.controller;

import com.duan.springboot.learning.thymeleaf.pojo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * 学生controller接口
 * 1. 增、删、改、查询、查看
 * 2. restful风格的接口
 *
 * @author duanjw
 */
@RestController
@RequestMapping("student")
@Slf4j
public class StudentController {

    /**
     * 新增学生
     * @param student 学生
     * @return true、false
     */
    @PostMapping
    public boolean insert(@RequestBody Student student) {
        log.info("新增学生：{}", student);
        return true;
    }


    /**
     * 查询所有学生
     * @return 学生list
     */
    @GetMapping("list")
    public List<Student> select() {
        List<Student> studentList = Arrays.asList(new Student(1,"张三",new Date()), new Student(2,"李四",new Date()));
        return studentList;
    }

    /**
     * 根据id查看学生
     * @param id
     * @return Student
     */
    @GetMapping("/{id}")
    public Student findById(@PathVariable Integer id) {
        if(1 == id) {
            return new Student(1,"张三",new Date());
        }
        return new Student();
    }

    /**
     * 根据id修改学生
     * @param student
     * @return true、false
     */
    @PutMapping
    public boolean updateById(@RequestBody Student student) {
        log.info("根据id修改学生：{}", student);
        if(1 == student.getId()) {
            return true;
        }
        return false;
    }

    /**
     * 根据id删除学生
     * @param id
     * @return true、false
     */
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        log.info("根据id删除学生：{}", id);
        return true;
    }

}

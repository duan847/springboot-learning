package com.duan.springboot.learning.vaildator.controller;

import com.duan.springboot.learning.common.R;
import com.duan.springboot.learning.vaildator.common.EnumCodeMessage;
import com.duan.springboot.learning.vaildator.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public R<Boolean> insert(@Valid @RequestBody Student student) {
        System.out.println(EnumCodeMessage.AGE_BETWEEN_0_AND_120.getMsg());
        log.info("新增学生：{}", student);
        return R.ok(true);
    }


    /**
     * 查询所有学生
     * @return 学生list
     */
    @GetMapping("list")
    public List<Student> select() {
        List<Student> studentList = Arrays.asList(new Student(1L,"张三",new Date(),"553343346@qq.com"), new Student(2L,"李四",new Date(),"55343346@qq.com"));
        return studentList;
    }

    /**
     * 根据id查看学生
     * @param id
     * @return Student
     */
    @GetMapping("/{id}")
    public R<Student> findById(@PathVariable Integer id) {
        if(1 == id) {
            return R.ok(new Student(1L,"张三",new Date(),"554343346@qq.com"));
        }
        return R.failed("用户不存在");
    }

    /**
     * 根据id修改学生
     * @param student
     * @return true、false
     */
    @PutMapping
    public R<Boolean> updateById(@RequestBody Student student) {
        log.info("根据id修改学生：{}", student);
        if(1 == student.getId()) {
            return R.ok(true);
        }
        return R.failed("学生不存在");
    }

    /**
     * 根据id删除学生
     * @param id
     * @return true、false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> deleteById(@PathVariable Integer id) {
        log.info("根据id删除学生：{}", id);
        return R.ok(true);
    }

}

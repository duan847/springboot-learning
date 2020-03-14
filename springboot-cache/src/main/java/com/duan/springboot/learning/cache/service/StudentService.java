package com.duan.springboot.learning.cache.service;

import com.duan.springboot.learning.cache.entity.Student;

import java.util.List;

/**
 *
 * 学生service接口
 *
 * @author duanjw
 */
public interface StudentService {

    /**
     * 查询所有学生
     *
     * @return 学生list
     */
    List<Student> select(String a, String b);

    /**
     * 根据id查看学生
     *
     * @param id
     * @return Student
     */
    Student findById(Integer id);

    /**
     * 根据id修改学生
     *
     * @param student
     * @return true、false
     */
    boolean updateById(Student student);

    /**
     * 根据id修改学生并且返回更新后的学生
     *
     * @param student 更新后的
     * @return student 更新后的
     */
    Student updateByIdAndReturn(Student student);

    /**
     * 根据id删除学生
     *
     * @param id
     * @return true、false
     */
    boolean deleteById(Integer id);

    /**
     * 删除所有学生
     * <p>
     * 删除所有学生的缓存
     *
     * @return true、false
     */
    boolean deleteAll();
}

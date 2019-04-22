package com.duan.springboot.learning.thymeleaf.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * 学生实体类
 *
 * @author duanjw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    /**
     * 学生id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Student(String name, Date updateTime) {
        this.name = name;
        this.updateTime = updateTime;
    }
}

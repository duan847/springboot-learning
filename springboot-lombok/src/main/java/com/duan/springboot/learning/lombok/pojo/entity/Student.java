package com.duan.springboot.learning.lombok.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * 学生实体类
 * 1. 注解：@Data，会为类的所有属性自动生成getter/setter、equals、canEqual、hashCode、toString方法
 * 2. 注解：
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

}

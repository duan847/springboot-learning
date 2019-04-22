package com.duan.springboot.learning.redis.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 *
 * 学生实体类
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * 学生id
     */
    private Long id;

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

    public void show(){
        System.out.println(this);
    }

}

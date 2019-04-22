package com.duan.springboot.learning.swagger.ui.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("学生实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    /**
     * 学生id
     */
    @ApiModelProperty("学生id")
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    public Student(String name, Date updateTime) {
        this.name = name;
        this.updateTime = updateTime;
    }
}

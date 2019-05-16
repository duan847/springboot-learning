package com.duan.video.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 分数实体类
 * @author duanjw
 */
@Data
public class Score implements Serializable {

    /**
     * 分数id
     */
    private Integer id;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 分数
     */
    private BigDecimal score;

    /**
     * 学生id
     */
    private Integer studentId;

}

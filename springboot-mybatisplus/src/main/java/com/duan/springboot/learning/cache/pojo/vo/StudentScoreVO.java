package com.duan.springboot.learning.cache.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 学生分数VO
 *
 * @author duanjw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentScoreVO {

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

    /**
     * 分数id
     */
    private Integer scoreId;

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

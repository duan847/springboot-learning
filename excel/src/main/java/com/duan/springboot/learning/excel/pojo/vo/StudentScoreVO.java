package com.duan.springboot.learning.excel.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 学生分数VO
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
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

    private String errorMap;

    public StudentScoreVO(Integer id) {
        this.id = id;
    }
}

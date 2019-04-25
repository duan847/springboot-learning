package com.duan.springboot.learning.excel.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Teach {
    private Integer id;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 专业
     */
    private String major;
    /**
     * 学位
     */
    private String degree;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
}

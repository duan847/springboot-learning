package com.duan.springboot.learning.excel.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 培训情况
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Train {
    private Integer id;
    /**
     * 课程
     */
    private String course;
    /**
     * 培训机构
     */
    private String org;
    /**
     * 培训时间
     */
    private Date time;
}

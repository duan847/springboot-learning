package com.duan.springboot.learning.excel.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience {
    private Integer id;
    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 职位
     */
    private String position;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 薪酬
     */
    private BigDecimal money;
    /**
     * 证明人
     */
    private String provePerson;
    /**
     * 证明人电话
     */
    private String provePersonPhone;
    /**
     * 离职原因
     */
    private String reasonsForLeaving;
}

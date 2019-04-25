package com.duan.springboot.learning.excel.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Family {
    private Integer id;
    private String name;
    /**
     * 关系
     */
    private Integer relation;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 工作单位
     */
    private String workUnit;
    /**
     * 联系电话
     */
    private String telephone;

}

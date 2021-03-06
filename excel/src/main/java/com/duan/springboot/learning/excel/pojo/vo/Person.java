package com.duan.springboot.learning.excel.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * 人员对象
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Integer id;
    private String name;
    private Integer sex;
    private String phone;
    private String email;
    private String address;
    private Date birthday;
    /**
     * 优点
     */
    private String merit;

    /**
     * 缺点
     */
    private String shortcoming;

    /**
     * 英语水平
     */
    private String englishLevel;

    /**
     * 第二外语
     */
    private String foreignLanguages;

}

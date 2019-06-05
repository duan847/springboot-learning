package com.duan.springboot.learning.vaildator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 学生实体类
 *
 * @author duanjw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    /**
     * 学生id
     */
    private Long id;

    /**
     * 姓名
     * @NotNull 不能为空
     * @NotBlank 不能为空，并且去空格后也不为空
     */

    @NotNull
    @NotBlank
    @Length(min = 2, max = 10)
    @CreditCardNumber
    private String name;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 邮件地址
     */
    @Email
    private String email;

    /**
     * 年龄
     */
    @Range(min = 0, max = 120)
    public Integer age;

    public Student(String name, Date updateTime) {
        this.name = name;
        this.updateTime = updateTime;
    }

    public Student(Long id,String name, Date updateTime, String email) {
        this.id = id;
        this.name = name;
        this.updateTime = updateTime;
        this.email = email;
    }
}

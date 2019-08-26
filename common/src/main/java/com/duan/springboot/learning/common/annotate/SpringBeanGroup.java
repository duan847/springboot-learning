package com.duan.springboot.learning.common.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * spring bean分组
 * @author duanjw
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpringBeanGroup {

    /**
     * 分割字符串
     */
    String SPLIT_STR = ":";

    /**
     * 默认的分组名
     */
    String DEFAULT_GROUP = "default" + SPLIT_STR;

    /**
     * 分组名
     *
     * @return
     */
    String group() default DEFAULT_GROUP;

    /**
     * 唯一标识
     * @return
     */
    String value();

}

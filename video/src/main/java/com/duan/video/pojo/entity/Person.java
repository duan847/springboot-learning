package com.duan.video.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    /**
     * 分数id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;
    private Integer type;
    private Long videoId;
}

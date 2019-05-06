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
public class Dict implements Serializable {

    /**
     * 字典id
     */
    private Integer id;

    /**
     * 值
     */
    private String value;

    /**
     * 上级id
     */
    private Integer pid;
}

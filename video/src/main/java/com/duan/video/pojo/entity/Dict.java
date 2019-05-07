package com.duan.video.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 字典实体
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Dict implements Serializable {

    /**
     * 字典id
     */
    @ApiModelProperty("字典id")
    private Integer id;

    /**
     * 值
     */
    @ApiModelProperty("值")
    private String value;

    /**
     * 上级id
     */
    @ApiModelProperty("上级id")
    private Integer pid;
}

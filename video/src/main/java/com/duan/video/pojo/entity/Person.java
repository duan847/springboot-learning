package com.duan.video.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 人员实体
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Person implements Serializable {

    /**
     * 人员id
     */
    @ApiModelProperty("人员id")
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 人员类型
     */
    @ApiModelProperty("人员类型")
    private Integer type;

    /**
     * 视频id
     */
    @ApiModelProperty("视频id")
    private Long videoId;
}

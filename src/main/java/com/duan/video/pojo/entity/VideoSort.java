package com.duan.video.pojo.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 视频排序
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class VideoSort implements Serializable {

    /**
     * 视频排序id
     */
    @ApiModelProperty("视频排序id")
    private Long id;

    /**
     * 排序类型
     */
    @ApiModelProperty("排序类型")
    private Integer type;

    /**
     * 视频排序id
     */
    @ApiModelProperty("视频排序id")
    private Long videoId;

    /**
     * 排序号
     */
    private Integer sort;

}

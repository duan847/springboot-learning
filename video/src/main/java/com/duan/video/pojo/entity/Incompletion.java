package com.duan.video.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 未完结实体
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Incompletion implements Serializable {

    /**
     * 未完结视频id
     */
    @ApiModelProperty("未完结视频id")
    private Long id;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 已更新集数
     */
    @ApiModelProperty("已更新集数")
    private Integer haveCount;

    /**
     * 总集数
     */
    @ApiModelProperty("总集数")
    private Integer sumCount;

    /**
     * 视频id
     */
    @ApiModelProperty("视频id")
    private Long videoId;
}

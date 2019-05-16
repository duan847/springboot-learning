package com.duan.video.pojo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 视频播放地址实体
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class RouteUrl extends Model<RouteUrl> {

    /**
     * 视频播放地址id
     */
    @ApiModelProperty("视频播放地址id")
    private Long id;

    /**
     * 视频id
     */
    @ApiModelProperty("视频id")
    private Long videoId;

    /**
     * 视频名称
     */
    @ApiModelProperty("视频名称")
    private String name;

    /**
     * 线路
     */
    @ApiModelProperty("线路")
    private Integer line;

    /**
     * 播放地址
     */
    @ApiModelProperty("播放地址")
    private String url;

    /**
     * 时长
     */
    @ApiModelProperty("时长")
    private Double filmLength;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}

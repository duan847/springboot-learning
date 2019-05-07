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
 * 视频线路实体
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class VideoRoute extends Model<VideoRoute> {

    /**
     * 线路id
     */
    @ApiModelProperty("线路id")
    private Long id;

    /**
     * 线路
     */
    @ApiModelProperty("线路")
    private Integer line;

    /**
     * 视频id
     */
    @ApiModelProperty("视频id")
    private Long videoId;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}

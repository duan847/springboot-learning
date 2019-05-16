package com.duan.video.pojo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 电影实体类
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class Video extends Model<Video> {

    /**
     * 视频id
     */
    @ApiModelProperty("视频id")
    private Long id;

    /**
     * 视频名称
     */
    @ApiModelProperty("视频名称")
    private String name;

    /**
     * 视频英文名称
     */
    @ApiModelProperty("视频英文名称")
    private String en;

    /**
     * 主演名
     */
    @ApiModelProperty("主演名")
    private String staringName;

    /**
     * 导演名
     */
    @ApiModelProperty("导演名")
    private String directorName;

    /**
     * 封面
     */
    @ApiModelProperty("封面")
    private String cover;

    /**
     * 制作地区
     */
    @ApiModelProperty("制作地区")
    private Integer area;

    /**
     * 制作地区名称
     */
    @ApiModelProperty("制作地区名称")
    private String areaName;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private Integer type;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String typeName;

    /**
     * 别名
     */
    @ApiModelProperty("别名")
    private String alias;

    /**
     * 片长
     */
    @ApiModelProperty("片长")
    private Double filmLength;

    /**
     * 评分
     */
    @ApiModelProperty("评分")
    private BigDecimal score;

    /**
     * 备注。更新了多少集/共多少集
     */
    @ApiModelProperty("备注")
    private String remarks;

    /**
     * 年份
     */
    @ApiModelProperty("年份")
    private String year;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;
    /**
     * 更新时间临时
     */
    @ApiModelProperty("更新时间临时")
    private String updateTimeTmp;

    /**
     * 视频编号
     */
    @ApiModelProperty("视频编号")
    private Integer no;

    /**
     * 简介
     */
    @ApiModelProperty("简介")
    private String synopsis;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}

package com.duan.video.pojo.vo;

import com.duan.video.pojo.entity.Person;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * 视频详细
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class VideoDetailVO {

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
     * 导演列表
     */
    @ApiModelProperty("导演列表")
    private List<Person> directorList;

    /**
     * 主演列表
     */
    @ApiModelProperty("主演列表")
    private List<Person> staringList;

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
    private Long filmLength;

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
    private String updateTime;

    /**
     * 简介
     */
    @ApiModelProperty("简介")
    private String synopsis;
}

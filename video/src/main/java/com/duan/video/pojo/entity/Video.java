package com.duan.video.pojo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * 电影实体类
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Video extends Model<Video> {
    /**
     * 电影
     */
    private Long id;
    /**
     * 视频名称
     */
    private String name;
    /**
     * 视频英文名称
     */
    private String en;
    /**
     * 主演名
     */
    private String staringName;
    /**
     * 导演名
     */
    private String directorName;
    /**
     * 封面
     */
    private String cover;
    /**
     * 制作地区
     */
    private Integer area;
    /**
     * 制作地区
     */
    private String areaName;
    /**
     * 类型
     */
    private String typeName;
    /**
     * 别名
     */
    private String alias;
    /**
     * 片长
     */
    private long filmLength;
    /**
     * 评分
     */
    private BigDecimal score;
    /**
     * 备注。更新了多少集/共多少集
     */
    private String remarks;
    /**
     * 年份
     */
    private String year;
    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 视频号
     */
    private Integer no;

    /**
     * 简介
     */
    private String synopsis;


    public void show(){
        System.out.println(this);
    }

    @Override
    protected Serializable pkVal() {
        return id;
    }
}

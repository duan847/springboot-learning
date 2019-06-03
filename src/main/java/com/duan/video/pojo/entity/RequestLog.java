package com.duan.video.pojo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求日志
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class RequestLog extends Model<Video> {
    /**
     * 请求日志id
     */
    private Long id;
    /**
     * url
     */
    private String url;
    /**
     * 请求开始时间
     */
    private Date startTime;
    /**
     * 请求数据返回时间
     */
    private Date endTime;
    /**
     * 时间差
     */
    private Long timeDiff;
    /**
     * ip
     */
    private String ip;
    /**
     * http方法
     */
    private String httpMethod;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String method;
    /**
     * 参数
     */
    private String params;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}

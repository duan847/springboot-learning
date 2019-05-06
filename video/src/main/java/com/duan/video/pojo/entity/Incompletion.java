package com.duan.video.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 未完结
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Incompletion implements Serializable {

    /**
     * 未完结视频id
     */
    private Long id;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 已更新集数
     */
    private Integer haveCount;
    /**
     * 总集数
     */
    private Integer sumCount;

    /**
     * 视频id
     */
    private Long videoId;
}

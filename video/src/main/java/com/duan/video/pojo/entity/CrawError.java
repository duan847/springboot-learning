package com.duan.video.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CrawError implements Serializable {

    /**
     * 爬取异常id
     */
    private Long id;

    /**
     * 异常记录时间
     */
    private Date createTime;

    /**
     * 内容
     */
    private String content;

    /**
     * 视频编号
     */
    private Integer videoNo;
}

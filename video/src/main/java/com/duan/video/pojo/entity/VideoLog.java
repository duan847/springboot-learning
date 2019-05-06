package com.duan.video.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class VideoLog {
    private Long id;
    private Integer no;
    private Long videoId;
    private Integer type;
    private Date createTime;
}

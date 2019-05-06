package com.duan.video.pojo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author duanjw
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RouteUrl extends Model<RouteUrl> {
    private Long id;
    private Long videoId;
    private String name;
    private Integer line;
    private String url;
    @Override
    protected Serializable pkVal() {
        return id;
    }
}

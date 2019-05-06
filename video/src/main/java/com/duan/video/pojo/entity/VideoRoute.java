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
public class VideoRoute extends Model<VideoRoute> {
    private Long id;
    private Integer line;
    private Long videoId;
    @Override
    protected Serializable pkVal() {
        return id;
    }
}

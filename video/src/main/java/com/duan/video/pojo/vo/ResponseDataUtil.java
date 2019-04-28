package com.duan.video.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author duanjw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDataUtil<T> implements Serializable {
    private Integer code;
    private String msg;
    private Integer page;
    private Integer pagecount;
    private Integer limit;
    private Integer total;
    private T list;


}

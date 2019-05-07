package com.duan.video.common;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * 分页查询对象
 *
 * @param <T>
 */
public class Query<T> extends Page<T> {

    private Map<String, Object> params;


    public Query(Map<String, Object> params) {
        super(Long.parseLong(params.getOrDefault("current", 1).toString()), Long.parseLong(params.getOrDefault("size", 10).toString()));
        String orderByField = params.getOrDefault("orderByField", "").toString();
        if (StringUtils.isNotEmpty(orderByField)) {
            Boolean isAsc = Boolean.parseBoolean(params.getOrDefault("isAsc", Boolean.TRUE).toString());
            if (isAsc) {
                this.setAsc(orderByField);
            } else {
                this.setDesc(orderByField);
            }
        }
        params.remove("current");
        params.remove("size");
        params.remove("orderByField");
        params.remove("isAsc");
        this.setParams(params);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}

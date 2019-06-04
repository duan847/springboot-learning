package com.duan.springboot.learning.common;

/**
 * @author duanjw
 */
public interface ICodeMessage {
    /**
     * 错误编码 -1、失败 0、成功
     * @return
     */
    Integer getCode();

    /**
     * 错误描述
     * @return
     */
    String getMsg();
}

package com.duan.springboot.learning.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author duanjw
 */
@Data
@Accessors(chain = true)
public class R<T> implements Serializable {

    public static final Integer SUCCESS = 0;
    public static final Integer FAILED = 1;


    /**
     * 业务错误码
     */
    private Integer code;
    /**
     * 结果集
     */
    private T data;
    /**
     * 描述
     */
    private String msg;

    public R() {
        // to do nothing
    }

    public R(ICodeMessage codeMessage) {
        this.code = codeMessage.getCode();
        this.msg = codeMessage.getMsg();
    }

    public static <T> R<T> ok(T data) {
        Integer result = SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            result = FAILED;
        }
        return restResult(data, result, null);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, FAILED, msg);
    }

    public static <T> R<T> failed(ICodeMessage errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> R<T> restResult(T data, ICodeMessage errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> R<T> restResult(T data, Integer code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean ok() {
        return SUCCESS.equals(code);
    }
}

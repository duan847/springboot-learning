package com.duan.springboot.learning.vaildator.common;

import com.duan.springboot.learning.common.ICodeMessage;

/**
 * 返回前端的错误码和消息
 * @author duanjw
 */
public enum EnumCodeMessage implements ICodeMessage {
    /**
     * 年龄大于等于0小于等于120
     */
    AGE_BETWEEN_0_AND_120(100, "年龄大于等于0小于等于120"),
    /**
     * 成功
     */
    SUCCESS(0, "执行成功");

     final Integer code;
     final String msg;

    EnumCodeMessage(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static EnumCodeMessage fromCode(Integer code) {
        EnumCodeMessage[] ecs = EnumCodeMessage.values();
        for (EnumCodeMessage ec : ecs) {
            if (ec.getCode().equals(code)) {
                return ec;
            }
        }
        return SUCCESS;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" EnumCodeMessage:{code=%s, msg=%s} ", code, msg);
    }
}

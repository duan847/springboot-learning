package com.duan.springboot.learning.pay.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author duanjw
 */
@Data
public class OnlinePayVO {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 支付渠道 id
     */
    private String channelId;

    /**
     * 商户订单号
     */
    private String noOrder;

    /**
     * 交易金额
     */
    private BigDecimal moneyOrder;

    /**
     * 收款方银行账号
     */
    private String cardNo;

    /**
     * 收款方姓名
     */
    private String acctName;

    /**
     * 订单描述
     */
    private String infoOrder;

    /**
     * 对公对私标志
     */
    private Integer flagCard;

    /**
     * 付款结果
     */
    private String resultPay;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 版本号
     */
    private String apiVersion;

    /**
     * 签名方式
     */
    private String signType;

    /**
     * 签名
     */
    private String sign;

    /**
     * 接收异步通知的线上地址
     */
    private String notifyUrl;

    /**
     * 收款备注
     */
    private String memo;

    /**
     * 用户来源
     */
    private String platform;

    /**
     * 大额行号
     */
    private String prcptcd;

    /**
     * 银行编码
     */
    private String bankCode;

    /**
     * 收款银行名称
     */
    private String bankName;

    /**
     * 开户行所在省市编码
     */
    private String cityCode;

    /**
     * 内部回调 url
     */
    private String callBackUrl;

    /**
     * 其他参数
     */
    private Map params;
}

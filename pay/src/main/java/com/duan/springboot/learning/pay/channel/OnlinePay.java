package com.duan.springboot.learning.pay.channel;

import com.duan.springboot.learning.pay.vo.OnlinePayVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 在线支付
 * @author duanjw
 */
public interface OnlinePay {
    /**
     * 查看我的余额
     *
     * @return
     */
    BigDecimal getBalance();

    /**
     * 根据金额校验我的余额
     *
     * @param money
     * @return
     */
    Boolean checkBalanceByMoney(BigDecimal money);

    /**
     * 申请付款
     *
     * @param onlinePayVO
     * @return
     */
    Boolean applyPay(OnlinePayVO onlinePayVO);

    /**
     * 批量申请付款
     *
     * @param onlinePayVOS
     * @return
     */
    Boolean applyPays(List<OnlinePayVO> onlinePayVOS);

    /**
     * 接收回调付款结果
     *
     * @param result
     * @return
     */
    String payResult(String result);
}

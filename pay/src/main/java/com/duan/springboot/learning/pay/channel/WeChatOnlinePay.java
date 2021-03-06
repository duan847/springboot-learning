package com.duan.springboot.learning.pay.channel;

import com.duan.springboot.learning.pay.annotate.SpringBeanGroup;
import com.duan.springboot.learning.pay.vo.OnlinePayVO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 微信在线支付
 * @author duanjw
 */
@Component
@SpringBeanGroup("weChat")
public class WeChatOnlinePay implements OnlinePay {
    @Override
    public BigDecimal getBalance() {
        return BigDecimal.ZERO;
    }

    @Override
    public Boolean checkBalanceByMoney(BigDecimal money) {
        return null;
    }

    @Override
    public Boolean applyPay(OnlinePayVO onlinePayVO) {
        return null;
    }

    @Override
    public Boolean applyPays(List<OnlinePayVO> onlinePayVOS) {
        return null;
    }

    @Override
    public String payResult(String result) {
        return null;
    }
}

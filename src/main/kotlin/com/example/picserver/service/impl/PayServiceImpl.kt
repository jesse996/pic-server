package com.example.picserver.service.impl

import com.alipay.easysdk.factory.Factory
import com.example.picserver.service.PayService
import org.springframework.stereotype.Service

@Service
class PayServiceImpl:PayService {
    /**
     * 手机支付
     */
    override fun payWap(money: Long):String? {
        return Factory.Payment.Wap().pay("subject", "1", "0.01", "https://m.jesse233.top", "https://m.jesse233.top").body

    }

    /**
     * pc支付
     */
    override fun payPc(money: Long):String? {
        val result =Factory.Payment.Page().pay("subject","1","0.01","https://m.jesse233.top")
        return result.body
    }
}
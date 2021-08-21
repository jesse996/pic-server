package com.example.picserver.service.impl

import cn.dev33.satoken.stp.StpUtil
import com.alipay.easysdk.factory.Factory
import com.example.picserver.entity.SysOrder
import com.example.picserver.service.PayService
import com.example.picserver.service.SysOrderService
import org.springframework.stereotype.Service

@Service
class PayServiceImpl(val sysOrderService: SysOrderService) : PayService {
    /**
     * 手机支付
     */
    override fun payWap(money: Long): String? {
        return Factory.Payment.Wap()
            .pay("subject", "1", "0.01", "https://m.jesse233.top", "https://m.jesse233.top").body

    }

    /**
     * pc支付
     */
    override fun payPc(money: Long): String? {
        val result = Factory.Payment.Page().pay("subject", "1", "0.01", "https://m.jesse233.top")
        return result.body
    }

    /**
     * 生成订单
     */
    override fun createOrder(amount: Long, targetId: Long, type: Int): Any {
        val order = SysOrder()
        order.userId = StpUtil.getLoginIdAsLong()
        order.targetId = targetId
        order.type = type

        //重复下单
        val one = sysOrderService.ktQuery()
            .eq(SysOrder::userId, order.userId)
            .eq(SysOrder::targetId, targetId)
            .eq(SysOrder::type, order.type)
            .one()
        if (one != null) {
            return one
        }

        order.amount = amount
        sysOrderService.save(order)

        return order
    }
}
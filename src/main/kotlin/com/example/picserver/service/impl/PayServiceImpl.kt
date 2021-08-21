package com.example.picserver.service.impl

import cn.dev33.satoken.stp.StpUtil
import com.alipay.easysdk.factory.Factory
import com.example.picserver.entity.SysOrder
import com.example.picserver.entity.vo.OrderResp
import com.example.picserver.entity.vo.PayReq
import com.example.picserver.service.PayService
import com.example.picserver.service.SysOrderService
import org.springframework.stereotype.Service

@Service
class PayServiceImpl(val sysOrderService: SysOrderService) : PayService {
    /**
     * 手机支付
     */
    override fun payWap(payReq: PayReq): String? {
        val order = sysOrderService.getById(payReq.orderId)!!

        return Factory.Payment.Wap()
            .pay(
                "赞赏",
                order.id!!.toString(),
                (order.amount!! / 100).toString(),
                payReq.redirect,
                payReq.redirect
            ).body

    }

    /**
     * pc支付
     */
    override fun payPc(payReq: PayReq): String? {
        val order = sysOrderService.getById(payReq.orderId)!!
        return Factory.Payment.Page().pay(
            "赞赏",
            order.id!!.toString(),
            (order.amount!! / 100).toString(),
            payReq.redirect,
        ).body
    }

    /**
     * 生成订单
     */
    override fun createOrder(amount: Long, targetId: Long, type: Int): OrderResp {
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
            return OrderResp(one.id!!)
        }

        order.amount = amount
        sysOrderService.save(order)

        return OrderResp(order.id!!)
    }
}
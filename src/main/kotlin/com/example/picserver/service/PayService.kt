package com.example.picserver.service

import com.example.picserver.entity.vo.OrderResp
import com.example.picserver.entity.vo.PayReq
import javax.servlet.http.HttpServletRequest

interface PayService {
    /**
     * 手机支付
     */
    fun payWap(payReq: PayReq): String?

    /**
     * pc支付
     */
    fun payPc(payReq: PayReq): String?

    /**
     * 生成订单
     */
    fun createOrder(amount:Long,targetId:Long,type:Int): OrderResp

    /**
     * 阿里异步通知
     */
    fun aliNotify(request: HttpServletRequest): String

    /**
     * 付款成功后续
     */
    fun handlePaySuccess(orderId: Long, realAmount: Long)
}
package com.example.picserver.service

import com.example.picserver.entity.vo.OrderResp
import com.example.picserver.entity.vo.PayReq

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
}
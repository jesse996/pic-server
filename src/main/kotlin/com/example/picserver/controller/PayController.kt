package com.example.picserver.controller

import com.example.picserver.common.CommonResult
import com.example.picserver.entity.vo.OrderReq
import com.example.picserver.entity.vo.PayReq
import com.example.picserver.service.PayService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pay")
class PayController(val payService: PayService) {
    @ApiOperation("pc支付")
    @PostMapping("pc")
    fun payPc(payReq: PayReq) =
        CommonResult.success(payService.payPc(payReq))

    @ApiOperation("手机支付")
    @PostMapping("wap")
    fun payWap(payReq: PayReq) =
        CommonResult.success(payService.payWap(payReq))

    @ApiOperation("获取订单")
    @PostMapping("getOrder")
    fun createOrder(orderReq: OrderReq) =
        CommonResult.success(payService.createOrder(orderReq.amount,orderReq.targetId,orderReq.type))
}
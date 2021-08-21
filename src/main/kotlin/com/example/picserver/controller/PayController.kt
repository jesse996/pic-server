package com.example.picserver.controller

import com.example.picserver.common.CommonResult
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
    fun payPc() =
        CommonResult.success(payService.payPc(1))

    @ApiOperation("手机支付")
    @PostMapping("wap")
    fun payWap() =
        CommonResult.success(payService.payWap(1))

    @ApiOperation("获取订单")
    @PostMapping("getOrder")
    fun createOrder() =
        CommonResult.success(payService.createOrder(1,1,1))
}
package com.example.picserver.service.impl

import cn.dev33.satoken.stp.StpUtil
import cn.hutool.json.JSONUtil
import com.alipay.easysdk.factory.Factory
import com.example.picserver.const.PayStatusEnum
import com.example.picserver.entity.SysOrder
import com.example.picserver.entity.vo.OrderResp
import com.example.picserver.entity.vo.PayReq
import com.example.picserver.service.PayService
import com.example.picserver.service.SysOrderService
import mu.KotlinLogging.logger
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

private val logger = logger {}

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
            (order.amount!! * 1.0 / 100).toString(),
            payReq.redirect,
        ).body
    }

    /**
     * 生成订单
     */
    override fun createOrder(amount: Long, targetId: Long, type: Int): OrderResp {
        val order = SysOrder()
        if (StpUtil.isLogin()) {
            order.userId = StpUtil.getLoginIdAsLong()
        }
        order.targetId = targetId
        order.type = type

        if (order.userId != null) {
            //重复下单
            val one = sysOrderService.ktQuery()
                .eq(SysOrder::userId, order.userId)
                .eq(SysOrder::targetId, targetId)
                .eq(SysOrder::type, order.type)
                .one()
            if (one != null) {
                return OrderResp(one.id!!)
            }
        }

        order.amount = amount
        sysOrderService.save(order)

        return OrderResp(order.id!!)
    }

    /**
     * 阿里异步通知
     */
    override fun aliNotify(request: HttpServletRequest): String {
        logger.info { "---支付宝回调开始----" }
        val params: MutableMap<String, String> = HashMap()
        val requestParams: Map<String, Array<String>> = request.parameterMap
        for (name in requestParams.keys) {
            val values = requestParams[name]!!
            var valueStr = ""
            for (i in values.indices) {
                valueStr = if (i == values.size - 1) valueStr + values[i] else valueStr + values[i] + ","
            }
            params[name] = valueStr
        }
        logger.info { "支付宝回调参数: ${JSONUtil.toJsonStr(params)}" }
        try {
            //验证签名
            val flag = Factory.Payment.Common().verifyNotify(params)
            if (flag) {
                if ("TRADE_SUCCESS" == params["trade_status"]) { //业务逻辑处理
                    //付款金额
                    val amount = params["buyer_pay_amount"]
                    //商户订单号
                    val out_trade_no = params["out_trade_no"]
                    //支付宝交易号
                    val trade_no = params["trade_no"]
                    //附加数据
                    val passback_params = params["passback_params"]
                    logger.info { "支付宝回调成功: $trade_no, $out_trade_no, $amount,$passback_params" }

                    //付款成功后业务逻辑
                    this.handlePaySuccess(out_trade_no!!.toLong(), (amount!!.toDouble() * 100).toLong())

                    return "success"
                }
            } else {
                logger.error { "【支付宝支付--异步回调】回调失败,签名验证失败" }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error { "【支付宝支付--异步回调】回调失败,原因为: $e.message" }
        }

        return "failure"
    }

    /**
     * 付款成功后续
     */
    override fun handlePaySuccess(orderId: Long, realAmount: Long) {
        val order = sysOrderService.getById(orderId)
        if (order.amount != realAmount) throw RuntimeException("付款金额错误")
        order.status = PayStatusEnum.SUCCESS.code
        sysOrderService.updateById(order)
    }
}
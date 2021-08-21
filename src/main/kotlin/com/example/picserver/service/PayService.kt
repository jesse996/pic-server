package com.example.picserver.service

interface PayService {
    /**
     * 手机支付
     */
    fun payWap(money: Long): String?

    /**
     * pc支付
     */
    fun payPc(money: Long): String?
}
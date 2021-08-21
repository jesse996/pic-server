package com.example.picserver.entity.vo

data class OrderReq(
    val type: Int,
    val targetId: Long,
    val amount: Long
)
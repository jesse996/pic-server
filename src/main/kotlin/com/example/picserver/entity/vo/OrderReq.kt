package com.example.picserver.entity.vo

import io.swagger.annotations.ApiModelProperty

data class OrderReq(
    @field:ApiModelProperty("0：赞赏作品，1：赞赏文章,2:买作品，3充值Vip")
    val type: Int,
    @field:ApiModelProperty("目标id")
    val targetId: Long,
    @field:ApiModelProperty("金额，单位分")
    val amount: Long,
    @field:ApiModelProperty("type为3时代表充值月数")
    val extra:String
)
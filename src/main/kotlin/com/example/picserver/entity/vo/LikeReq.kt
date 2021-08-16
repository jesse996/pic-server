package com.example.picserver.entity.vo

import io.swagger.annotations.ApiModelProperty

class LikeReq(
    @ApiModelProperty("0:pic ,1:news")
    val type: Int,
    @ApiModelProperty("点赞对象的id")
    val objId: Long
)
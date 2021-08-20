package com.example.picserver.entity.vo

import javax.validation.constraints.Email

data class UserSignUpReq(
    @field:Email(regexp = "^(.+)@(.+)\$",message = "邮箱不合法")
    val username: String,
    val password: String,
    val nickname: String,
)
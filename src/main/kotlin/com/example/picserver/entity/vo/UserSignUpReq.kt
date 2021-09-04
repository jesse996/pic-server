package com.example.picserver.entity.vo

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class UserSignUpReq(
    @field:Email(regexp = "^(.+)@(.+)\$",message = "邮箱不合法")
    val username: String,
    @field:NotBlank
    val password: String,
    @field:NotBlank
    val nickname: String,
    @field:NotBlank
    val code:String
)
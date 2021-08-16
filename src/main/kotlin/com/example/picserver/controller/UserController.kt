package com.example.picserver.controller;


import com.example.picserver.common.CommonResult
import com.example.picserver.entity.vo.UserSignInReq
import com.example.picserver.entity.vo.UserSignUpReq
import com.example.picserver.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {
    @PostMapping("signUp")
    fun signUp(@RequestBody user: UserSignUpReq) =
        CommonResult.success(userService.signUp(user))

    @PostMapping("signIn")
    fun signIn(@RequestBody user: UserSignInReq) =
        CommonResult.success(userService.signIn(user))

}


package com.example.picserver.controller;


import com.example.picserver.common.CommonResult
import com.example.picserver.entity.vo.UserSignInReq
import com.example.picserver.entity.vo.UserSignUpReq
import com.example.picserver.service.UserService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

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

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("")
    fun getCurrent() =
        CommonResult.success(userService.current())
}


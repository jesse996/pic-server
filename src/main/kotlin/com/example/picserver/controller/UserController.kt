package com.example.picserver.controller;


import com.example.picserver.common.CommonResult
import com.example.picserver.entity.vo.SendCodeReq
import com.example.picserver.entity.vo.UserSignInReq
import com.example.picserver.entity.vo.UserSignUpReq
import com.example.picserver.service.UserService
import io.swagger.annotations.ApiOperation
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
@Validated
class UserController(val userService: UserService) {
    @PostMapping("signUp")
    fun signUp(@Valid @RequestBody user: UserSignUpReq) =
        CommonResult.success(userService.signUp(user))

    @PostMapping("signIn")
    fun signIn(@RequestBody user: UserSignInReq) =
        CommonResult.success(userService.signIn(user))

    @PostMapping("logout")
    fun logout() =
        CommonResult.success(userService.logout())

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("")
    fun getCurrent() =
        CommonResult.success(userService.current())

    @ApiOperation("用户激活")
    @GetMapping("/enable/{encode}")
    fun enable(@PathVariable("encode") encode: String) =
        CommonResult.success(userService.enable(encode))

    @ApiOperation("发送注册邮箱验证码")
    @PostMapping("sendSignUpCode")
    fun sendSignUpCode(@RequestBody sendCodeReq: SendCodeReq) =
        CommonResult.success(userService.sendSignUpCode(sendCodeReq.email))

}


package com.example.picserver.common

import cn.dev33.satoken.exception.NotLoginException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBindException(e: MethodArgumentNotValidException): CommonResult<String> {
        val br: BindingResult = e.bindingResult
        val msgStr = StringBuilder()
        val feList = br.fieldErrors
        for (fe in feList) {
            val message = fe.defaultMessage
            val field = fe.field
            msgStr.append(field).append(message).append("; ")
        }

        val commonResult = CommonResult<String>()
        commonResult.code = 400
        commonResult.msg = "validate error"
        commonResult.data = msgStr.toString()
        return commonResult
    }

    // 全局异常拦截（拦截项目中的NotLoginException异常）
    @ExceptionHandler(NotLoginException::class)
    @Throws(Exception::class)
    fun handlerNotLoginException(
        nle: NotLoginException,
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ): CommonResult<Nothing> {
        // 打印堆栈，以供调试
        nle.printStackTrace()

        // 判断场景值，定制化异常信息
        val message = when (nle.type) {
            NotLoginException.NOT_TOKEN -> "未提供token"
            NotLoginException.INVALID_TOKEN -> "token无效"
            NotLoginException.TOKEN_TIMEOUT -> "token已过期"
            NotLoginException.BE_REPLACED -> "token已被顶下线"
            NotLoginException.KICK_OUT -> "token已被踢下线"
            else -> "当前会话未登录"
        }
        // 返回给前端
        return CommonResult.fail(message)
    }

    @ExceptionHandler
    fun handleUnknownError(e: RuntimeException): CommonResult<Nothing> {
        e.printStackTrace()
        return CommonResult.fail(e.localizedMessage)
    }
}
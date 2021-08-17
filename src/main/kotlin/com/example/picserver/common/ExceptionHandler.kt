package com.example.picserver.common

import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBindException(e: MethodArgumentNotValidException): CommonResult<String> {
        val commonResult = CommonResult<String>()
        commonResult.code = 400
        commonResult.msg = "validate error"
        commonResult.data = e.message
        return commonResult
    }

    @ExceptionHandler
    fun handleUnknownError(e: RuntimeException): CommonResult<Nothing> {
        return CommonResult.fail(e.localizedMessage)
    }
}
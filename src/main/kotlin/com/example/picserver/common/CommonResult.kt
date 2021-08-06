package com.example.picserver.common

data class CommonResult<T>(val code: Int = 0, var msg: String = "", var data: T? = null) {
    companion object {
        fun <T> success(data: T? = null) = CommonResult(code = 0, msg = "ok", data)
        fun fail(msg: String) = CommonResult(code = 1, msg, null)
    }
}
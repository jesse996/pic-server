package com.example.picserver.common

import java.io.Serializable

data class CommonResult<T>(val code: Int = 0, var msg: String = "", var data: T? = null) : Serializable {
    companion object {
        fun <T> success(data: T? = null) = CommonResult(code = 0, msg = "ok", data)
        fun fail(msg: String) = CommonResult(code = 1, msg, null)
    }
}
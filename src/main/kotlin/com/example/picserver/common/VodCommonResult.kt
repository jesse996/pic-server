package com.example.picserver.common

data class VodCommonResult<T>(
    var code: Int,
    var limit: String,
    var list: T,
    var msg: String,
    val page: Long,
    var pagecount: Long,
    var total: Long
) {
    companion object {
        fun <T> success(data: T, msg: String, page: Long = 1, pagecount: Long = 1, total: Long = 1,limit: Long=20) =
            VodCommonResult(code = 0, limit = limit.toString(), list = data, msg = msg, page, pagecount, total)
    }
}
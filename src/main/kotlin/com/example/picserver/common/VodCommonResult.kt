package com.example.picserver.common

import com.example.picserver.entity.SysVodClass

data class VodCommonResult<T>(
    var code: Int,
    var `class`: List<SysVodClass>?,
    var limit: String,
    var list: T,
    var msg: String,
    val page: Long,
    var pagecount: Long,
    var total: Long
) {
    companion object {
        fun <T> success(
            data: T,
            msg: String,
            `class`: List<SysVodClass>? = null,
            page: Long = 1,
            pagecount: Long = 1,
            total: Long = 1,
            limit: Long = 20
        ) =
            VodCommonResult(
                code = 0,
                limit = limit.toString(),
                list = data,
                msg = msg,
                `class` = `class`,
                page = page,
                pagecount = pagecount,
                total = total
            )
    }
}
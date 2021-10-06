package com.example.picserver.entity.vo

import com.example.picserver.entity.SysVod

class VodResp(
    var classList: MutableList<VodClass>?,
    var code: Int?,
    var limit: String?,
    var list: List<SysVod>?,
    var msg: String?,
    var page: Int?,
    var pagecount: Int?,
    var total: Int?
) {
    override fun toString(): String {
        return "VodResp(classList=$classList, code=$code, limit=$limit, list=$list, msg=$msg, page=$page, pagecount=$pagecount, total=$total)"
    }
}
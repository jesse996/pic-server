package com.example.picserver.entity.vo

import com.example.picserver.entity.SysVod
import com.example.picserver.entity.SysVodDetail

class VodDetailResp(
    var code: Int?,
    var limit: String?,
    var list: List<SysVodDetail>?,
    var msg: String?,
    var page: Int?,
    var pagecount: Int?,
    var total: Int?
) {
    override fun toString(): String {
        return "VodDetailResp(code=$code, limit=$limit, list=$list, msg=$msg, page=$page, pagecount=$pagecount, total=$total)"
    }
}
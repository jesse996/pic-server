package com.example.picserver.common

import cn.hutool.http.HttpUtil
import cn.hutool.json.JSONUtil
import com.aliyun.tea.okhttp.OkHttpClientBuilder
import okhttp3.OkHttpClient

//class Utils{
//    object getHttpClient
//}

fun getHttpClient(): OkHttpClient {
    val res = HttpUtil.get("http://127.0.0.1:5010/get/")
    val parseObj = JSONUtil.parseObj(res)
    val builder = OkHttpClientBuilder()
    val p = (if (parseObj["https"] as Boolean) "http://" else "https://") + parseObj["proxy"]
    builder.proxy(
        mapOf("httpProxy" to p)
    )
    return builder.buildOkHttpClient()
}

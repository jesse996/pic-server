package com.example.picserver.service.impl;

import cn.hutool.http.HttpUtil
import cn.hutool.json.JSONArray
import cn.hutool.json.JSONObject
import cn.hutool.json.JSONUtil
import com.aliyun.tea.okhttp.OkHttpClientBuilder
import com.example.picserver.entity.SysVod;
import com.example.picserver.mapper.SysVodMapper;
import com.example.picserver.service.SysVodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.common.getHttpClient
import com.example.picserver.entity.vo.VodClass
import com.example.picserver.entity.vo.VodResp
import okhttp3.Request
import org.springframework.stereotype.Service

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-09-28
 */
@Service
open class SysVodServiceImpl : ServiceImpl<SysVodMapper, SysVod>(), SysVodService {
    override fun spiderAll() {
        var res: String = HttpUtil.get("https://api.apibdzy.com/api.php/provide/vod/?ac=list")
        val vodResp = tranVod(res)
        this.saveOrUpdateBatch(vodResp.list)

        var client = getHttpClient()

        var i = 2
        while (i <= vodResp.pagecount!!) {
            try {
                val request =
                    Request.Builder().url("https://api.apibdzy.com/api.php/provide/vod/?ac=list&pg=$i").build()
                val response = client.newCall(request).execute()
                res = response.body()!!.string()
                println("i=$i")
                val tranVod = tranVod(res)
                this.saveOrUpdateBatch(tranVod.list)
            } catch (e: Exception) {
                client = getHttpClient()
                println(e.localizedMessage)
                i -= 1
            }
            i += 1
        }
    }


    fun tranVod(res: String): VodResp {
        val parse = JSONUtil.parseObj(res)
        val vodResp: VodResp = JSONUtil.toBean(res, VodResp::class.java)
        vodResp.classList = parse.getJSONArray("class").toList(VodClass::class.java)
        return vodResp
    }

    override fun spiderByHour(hours: Int) {
        //爬vod
        var res: String = HttpUtil.get("https://api.apibdzy.com/api.php/provide/vod/?ac=list&h=$hours")
        val vodResp = tranVod(res)
        this.saveOrUpdateBatch(vodResp.list)

        var client = getHttpClient()

        var i = 2
        while (i <= vodResp.pagecount!!) {
            try {
                val request =
                    Request.Builder().url("https://api.apibdzy.com/api.php/provide/vod/?ac=list&h=$hours&pg=$i").build()
                val response = client.newCall(request).execute()
                res = response.body()!!.string()
                println("i=$i")
                val tranVod = tranVod(res)
                this.saveOrUpdateBatch(tranVod.list)
            } catch (e: Exception) {
                client = getHttpClient()
                println(e.localizedMessage)
                i -= 1
            }
            i += 1
        }
    }
}
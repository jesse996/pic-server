package com.example.picserver.service.impl;

import cn.hutool.http.HttpUtil
import cn.hutool.json.JSONUtil
import com.example.picserver.entity.SysVodDetail;
import com.example.picserver.mapper.SysVodDetailMapper;
import com.example.picserver.service.SysVodDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.common.getHttpClient
import com.example.picserver.entity.vo.VodDetailResp
import okhttp3.Request
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-09-28
 */
@Service
open class SysVodDetailServiceImpl : ServiceImpl<SysVodDetailMapper, SysVodDetail>(), SysVodDetailService {
    /**
     * 爬 vod detail
     */
    override fun spiderDetail(){
        var res: String = HttpUtil.get("https://api.apibdzy.com/api.php/provide/vod/?ac=detail")
        val vodResp = tranVodDetail(res)
        this.saveOrUpdateBatch(vodResp.list)

        var client = getHttpClient()

        var i = 2
        while (i <= vodResp.pagecount!!) {
            try {
                val request =
                    Request.Builder().url("https://api.apibdzy.com/api.php/provide/vod/?ac=detail&pg=$i").build()
                val response = client.newCall(request).execute()
                res = response.body()!!.string()
                println("i=$i")
                val tranVod = tranVodDetail(res)
                this.saveOrUpdateBatch(tranVod.list)
            } catch (e: Exception) {
                client = getHttpClient()
                println(e.localizedMessage)
                i -= 1
            }
            i += 1
        }
    }

    fun tranVodDetail(res: String): VodDetailResp {
        return JSONUtil.toBean(res, VodDetailResp::class.java)
    }

    override fun spiderByHour(hour: Int) {
        var res: String = HttpUtil.get("https://api.apibdzy.com/api.php/provide/vod/?ac=detail&h=$hour")
        val vodResp = tranVodDetail(res)
        this.saveOrUpdateBatch(vodResp.list)

        var client = getHttpClient()

        var i = 2
        while (i <= vodResp.pagecount!!) {
            try {
                val request =
                    Request.Builder().url("https://api.apibdzy.com/api.php/provide/vod/?ac=detail&pg=$i&h=$hour").build()
                val response = client.newCall(request).execute()
                res = response.body()!!.string()
                println("i=$i")
                val tranVod = tranVodDetail(res)
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

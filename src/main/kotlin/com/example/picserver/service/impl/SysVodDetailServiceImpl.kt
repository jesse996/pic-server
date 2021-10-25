package com.example.picserver.service.impl;

import cn.hutool.core.collection.CollUtil
import cn.hutool.http.HttpUtil
import cn.hutool.json.JSONUtil
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.picserver.entity.SysVodDetail;
import com.example.picserver.mapper.SysVodDetailMapper;
import com.example.picserver.service.SysVodDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.common.VodCommonResult
import com.example.picserver.common.getHttpClient
import com.example.picserver.entity.vo.VodDetailResp
import okhttp3.Request
import org.springframework.stereotype.Service;
import java.time.LocalDateTime

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
    override fun spiderDetail() {
        var res: String = HttpUtil.get("https://api.apibdzy.com/api.php/provide/vod/?ac=detail")
        val vodResp = tranVodDetail(res)
        this.saveOrUpdateBatch(vodResp.list)

        var client = getHttpClient()

        var i = 2
        val total = vodResp.pagecount!!
        while (i <= total) {
            try {
                val request =
                    Request.Builder().url("https://api.apibdzy.com/api.php/provide/vod/?ac=detail&pg=$i").build()
                val response = client.newCall(request).execute()
                res = response.body()!!.string()
                println("vod detail -------- $i/$total")

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
                    Request.Builder().url("https://api.apibdzy.com/api.php/provide/vod/?ac=detail&pg=$i&h=$hour")
                        .build()
                val response = client.newCall(request).execute()
                res = response.body()!!.string()
                println("vod detail: i=$i")
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

    override fun get(
        t: Long?,
        pg: Long,
        wd: String?,
        h: Long?,
        ids: List<Long>?,
        limit: Long?
    ): VodCommonResult<List<SysVodDetail>?> {
        try {
            val page = this.ktQuery()
                .eq(t != null, SysVodDetail::typeId, t)
                .`in`(CollUtil.isNotEmpty(ids), SysVodDetail::vodId, ids)
                .like(wd != null, SysVodDetail::vodName, wd)
                .gt(h != null, SysVodDetail::vodTime, LocalDateTime.now().minusHours(h ?: 0))
                .page(Page(pg, limit ?: 20))

            return VodCommonResult.success(
                page.records,
                msg = "数据列表",
                `class` = null,
                page.current,
                page.pages,
                page.total,
                page.size
            )
        } catch (e: Exception) {
            println("vod detail")
            println(e.localizedMessage)
        }
        return VodCommonResult.success(null,"空")
    }
}

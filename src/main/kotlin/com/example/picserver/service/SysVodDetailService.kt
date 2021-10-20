package com.example.picserver.service;

import com.example.picserver.entity.SysVodDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.picserver.common.CommonResult
import com.example.picserver.common.VodCommonResult

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesse
 * @since 2021-09-28
 */
interface SysVodDetailService : IService<SysVodDetail>{
    fun spiderDetail()
    fun spiderByHour(hour: Int)
    fun get (t: Long?, pg: Long, wd: String?, h: Long?, ids: List<Long>?,limit:Long?): VodCommonResult<List<SysVodDetail>?>
}

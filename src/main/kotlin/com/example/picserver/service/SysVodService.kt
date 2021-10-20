package com.example.picserver.service;

import com.example.picserver.entity.SysVod;
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
interface SysVodService : IService<SysVod> {
    fun spiderAll()
    fun spiderByHour(hours:Int)
    fun get(t: Long?, pg: Long, wd: String?, h: Long?,limit:Long?): VodCommonResult<List<SysVod>?>
}

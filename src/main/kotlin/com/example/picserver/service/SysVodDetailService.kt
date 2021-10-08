package com.example.picserver.service;

import com.example.picserver.entity.SysVodDetail;
import com.baomidou.mybatisplus.extension.service.IService;

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
    fun spiderByHour(i: Int)
}

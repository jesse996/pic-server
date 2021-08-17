package com.example.picserver.service;

import com.example.picserver.entity.ViewCount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 浏览量表 服务类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
interface ViewCountService : IService<ViewCount> {
    fun increase(id: Long, type: Int)
    fun getByPicId(id: Long): ViewCount?
    fun getByNewsId(id: Long): ViewCount?
}

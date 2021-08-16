package com.example.picserver.service;

import com.example.picserver.entity.NewsTag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
interface NewsTagService : IService<NewsTag> {
    fun setNewsTags(id: Long, tags: List<String>)
}

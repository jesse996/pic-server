package com.example.picserver.service;

import com.example.picserver.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 轮播图表 服务类
 * </p>
 *
 * @author jesse
 * @since 2021-08-06
 */
interface NewsService : IService<News> {
    abstract fun search(keyword: String): List<News>
}

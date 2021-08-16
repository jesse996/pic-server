package com.example.picserver.service;

import com.example.picserver.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.picserver.entity.vo.NewsVo

/**
 * <p>
 * 轮播图表 服务类
 * </p>
 *
 * @author jesse
 * @since 2021-08-06
 */
interface NewsService : IService<News> {
    fun search(keyword: String): List<News>
    fun saveNews(news: NewsVo): Boolean
    fun getNewsById(id: Long): NewsVo
}

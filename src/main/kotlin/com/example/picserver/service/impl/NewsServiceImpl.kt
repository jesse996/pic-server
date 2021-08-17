package com.example.picserver.service.impl;

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.collection.CollUtil
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.picserver.entity.News;
import com.example.picserver.mapper.NewsMapper;
import com.example.picserver.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.entity.Pic
import com.example.picserver.entity.vo.NewsVo
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.service.CategoryService
import com.example.picserver.service.NewsTagService
import org.springframework.stereotype.Service;

/**
 * <p>
 * 轮播图表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-06
 */
@Service
open class NewsServiceImpl(val categoryService: CategoryService, val newsTagService: NewsTagService) :
    ServiceImpl<NewsMapper, News>(), NewsService {
    override fun search(keyword: String): List<News> =
        this.ktQuery()
            .like(News::title, keyword)
            .or()
            .like(News::description, keyword)
            .list()

    override fun saveNews(news: NewsVo): Boolean {
        //防止重复
        if (this.ktQuery().eq(News::coverImg, news.coverImg).count() > 0) {
            return false
        }

        this.save(news)

        //设置类别
        if (news.category != null) {
            val category = categoryService.getByName(news.category!!)
            news.categoryId = category.id
        }

        //设置标签
        if (CollUtil.isNotEmpty(news.tags)) {
            newsTagService.setNewsTags(news.id!!, news.tags!!)
        }
        return true
    }

    override fun getNewsById(id: Long): NewsVo? {
        val news = this.getById(id) ?: return null
        val res = BeanUtil.toBean(news, NewsVo::class.java)

        res.category = categoryService.getById(res.categoryId)?.name
        res.tags = newsTagService.getNewsTags(res.id)
        return res
    }

    override fun getPage(pageReq: PageReq<News>): IPage<NewsVo> {
        val page = Page<News>(pageReq.current, pageReq.size)
        val wrapper = if (pageReq.data == null) {
            KtQueryWrapper(News())
        } else {
            KtQueryWrapper(pageReq.data)
        }.orderByDesc(Pic::createTime)
        return this.page(page, wrapper).convert { getNewsById(it.id!!) }
    }
}

package com.example.picserver.service.impl;

import com.example.picserver.entity.NewsTag;
import com.example.picserver.mapper.NewsTagMapper;
import com.example.picserver.service.NewsTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.entity.Tag
import com.example.picserver.service.TagService
import org.springframework.stereotype.Service;
import java.util.*
import kotlin.math.tan

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@Service
open class NewsTagServiceImpl(val tagService: TagService) : ServiceImpl<NewsTagMapper, NewsTag>(), NewsTagService {
    override fun setNewsTags(id: Long, tags: List<String>) {
        for (tagName in tags) {
            var tag = tagService.getByName(tagName)
            if (tag ==null){
                val tmp = Tag()
                tmp.name = tagName
                tmp.type = 1
                tagService.save(tmp)
                tag = tmp
            }

            val newsTag = NewsTag()
            newsTag.tagId = tag.id
            newsTag.newsId = id
            this.save(newsTag)
        }
    }

    override fun getNewsTags(id: Long?): List<String> {
        val tagIds = this.ktQuery()
            .eq(NewsTag::newsId, id)
            .list()
        return tagIds.map { tagService.getById(it)?.name?:"" }.filter { it.isNotBlank()}
    }
}

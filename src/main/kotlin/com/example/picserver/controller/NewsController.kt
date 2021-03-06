package com.example.picserver.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.example.picserver.common.CommonResult
import com.example.picserver.const.ViewCountEnum
import com.example.picserver.entity.News
import com.example.picserver.entity.vo.NewsVo
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.service.NewsService
import com.example.picserver.service.ViewCountService
import io.swagger.annotations.ApiOperation
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("news")
@CacheConfig(cacheNames = ["news"])
class NewsController(val newsService: NewsService,val viewCountService: ViewCountService) {
    @ApiOperation("分页查询")
    @Cacheable
    @PostMapping("list")
    fun list(@RequestBody pageReq: PageReq<News>): CommonResult<IPage<NewsVo>> {
        return CommonResult.success(newsService.getPage(pageReq))
    }

    @Cacheable(key = "#id")
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long):CommonResult<NewsVo?> {
        //浏览量+1
        viewCountService.increase(id, ViewCountEnum.NEWS.code)
        return CommonResult.success(newsService.getNewsById(id))
    }

    @PostMapping("")
    fun add(@RequestBody news: NewsVo) = CommonResult.success(newsService.saveNews(news))

    @CacheEvict(key = "#id")
    @DeleteMapping("/{id}")
    fun delById(@PathVariable("id") id: Long) = CommonResult.success(newsService.removeById(id))

    @CachePut(key = "#news.id")
    @PutMapping("")
    fun updateById(@RequestBody news: News) = CommonResult.success(newsService.updateById(news))

    @ApiOperation("搜索")
    @Cacheable
    @GetMapping("search")
    fun search(@PathParam("keyword") keyword: String) = CommonResult.success(newsService.search(keyword))
}
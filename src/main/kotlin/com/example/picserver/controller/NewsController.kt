package com.example.picserver.controller

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.picserver.common.CommonResult
import com.example.picserver.entity.News
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.service.NewsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("news")
class NewsController(val newsService: NewsService) {
    @PostMapping("list")
    fun list(@RequestBody pageReq: PageReq<News>): CommonResult<Page<News>> {
        val page = Page<News>(pageReq.current, pageReq.size)
        val wrapper = LambdaQueryWrapper<News>(pageReq.data)
        return CommonResult.success(newsService.page(page, wrapper))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long) = CommonResult.success(newsService.getById(id))

    @PostMapping("/")
    fun add(@RequestBody news: News) = CommonResult.success(newsService.save(news))

    @DeleteMapping("/{id}")
    fun delById(@PathVariable("id") id: Long) = CommonResult.success(newsService.removeById(id))

    @PutMapping("/")
    fun updateById(@RequestBody news: News) = CommonResult.success(newsService.updateById(news))
}
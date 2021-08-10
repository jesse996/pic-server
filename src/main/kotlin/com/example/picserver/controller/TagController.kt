package com.example.picserver.controller

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.example.picserver.common.CommonResult
import com.example.picserver.entity.Tag
import com.example.picserver.service.TagService
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tag")
@CacheConfig(cacheNames = ["tag"])
class TagController(val tagService: TagService) {
    @Cacheable
    @PostMapping("list")
    fun list(@RequestBody tag: Tag): CommonResult<List<Tag>> {
        val wrapper = LambdaQueryWrapper(tag)
        return CommonResult.success(tagService.list(wrapper))
    }

    @Cacheable(key = "#id")
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long) = CommonResult.success(tagService.getById(id))

    @PostMapping("")
    fun add(@RequestBody tag: Tag): CommonResult<Boolean> {
        if (tagService.ktQuery().eq(Tag::name, tag.name).eq(Tag::type, tag.type).count() > 0) {
            return CommonResult.success(false);
        }
        return CommonResult.success(tagService.save(tag))
    }

    @CacheEvict(key = "#id")
    @DeleteMapping("/{id}")
    fun delById(@PathVariable("id") id: Long) = CommonResult.success(tagService.removeById(id))

    @CachePut(key = "#tag.id")
    @PutMapping("")
    fun updateById(@RequestBody tag: Tag) = CommonResult.success(tagService.updateById(tag))

}
package com.example.Tagserver.controller

import cn.hutool.json.XMLTokener.entity
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.picserver.common.CommonResult
import com.example.picserver.entity.Tag
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.service.TagService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tag")
class TagController(val tagService: TagService) {
    @PostMapping("list")
    fun list(@RequestBody tag: Tag): CommonResult<List<Tag>> {
        val wrapper = LambdaQueryWrapper(tag)
        return CommonResult.success(tagService.list(wrapper))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long) = CommonResult.success(tagService.getById(id))

    @PostMapping("")
    fun add(@RequestBody tag: Tag): CommonResult<Boolean> {
        if (tagService.ktQuery().eq(Tag::name, tag.name).eq(Tag::type, tag.type).count() > 0) {
            return CommonResult.success(false);
        }
        return CommonResult.success(tagService.save(tag))
    }

    @DeleteMapping("/{id}")
    fun delById(@PathVariable("id") id: Long) = CommonResult.success(tagService.removeById(id))

    @PutMapping("")
    fun updateById(@RequestBody Tag: Tag) = CommonResult.success(tagService.updateById(Tag))

}
package com.example.picserver.controller

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.picserver.common.CommonResult
import com.example.picserver.entity.SysPost
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.service.SysPostService
import org.springframework.web.bind.annotation.*

@RequestMapping("post")
class PostController(
    private val sysPostService: SysPostService
) {
    @PostMapping("page")
    fun page(@RequestBody req: PageReq<SysPost> )=
        CommonResult.success(sysPostService.page(Page(req.current,req.size)))

    @GetMapping("{id}")
    fun get(@PathVariable("id") id:Long) =
        CommonResult.success(sysPostService.getById(id))

    @PostMapping("add")
    fun add(@RequestBody req:SysPost)=
        CommonResult.success(sysPostService.save(req))

    @PostMapping("update")
    fun update(@RequestBody req:SysPost) =
        CommonResult.success(sysPostService.updateById(req))

    @PostMapping("delete")
    fun delete(@RequestBody id:Long) =
        CommonResult.success(sysPostService.removeById(id))
}
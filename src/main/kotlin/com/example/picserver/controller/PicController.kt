package com.example.picserver.controller

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.picserver.common.CommonResult
import com.example.picserver.entity.Pic
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.service.PicService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("pic")
class PicController(val picService: PicService) {
    @PostMapping("list")
    fun list(@RequestBody pageReq: PageReq<Pic>): CommonResult<Page<Pic>> {
        val page = Page<Pic>(pageReq.current, pageReq.size)
        val wrapper = LambdaQueryWrapper<Pic>(pageReq.data)
        return CommonResult.success(picService.page(page, wrapper))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long) = CommonResult.success(picService.getById(id))

    @PostMapping("")
    fun add(@RequestBody pic: Pic) = CommonResult.success(picService.save(pic))

    @DeleteMapping("/{id}")
    fun delById(@PathVariable("id") id: Long) = CommonResult.success(picService.removeById(id))

    @PutMapping("")
    fun updateById(@RequestBody pic: Pic) = CommonResult.success(picService.updateById(pic))

}
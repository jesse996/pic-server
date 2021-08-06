package com.example.picserver.controller

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.picserver.common.CommonResult
import com.example.picserver.entity.Pic
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.service.PicService
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@RestController
@RequestMapping("cosplay")
class CosplayController(@Resource private val picService: PicService) {
    @PostMapping("")
    fun list(@RequestBody pageReq: PageReq) = CommonResult.success(picService.page(Page(pageReq.current, pageReq.size)))

    @GetMapping("/:id")
    fun getById(@RequestParam("id") id: Long) = CommonResult.success(picService.getById(id))

    @PostMapping("/")
    fun add(@RequestBody pic: Pic) = CommonResult.success(picService.save(pic))

    @DeleteMapping("/:id")
    fun delById(@RequestParam("id") id: Long) = CommonResult.success(picService.removeById(id))

    @PutMapping("/:id")
    fun updateById(@RequestBody pic: Pic) = CommonResult.success(picService.updateById(pic))
}
package com.example.picserver.controller

import com.example.picserver.common.CommonResult
import com.example.picserver.entity.Pic
import com.example.picserver.service.PicService
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@RestController
@RequestMapping("cosplay")
class CosplayController(@Resource private val picService: PicService) {
    @GetMapping("")
    fun list(): CommonResult<Any> =
        CommonResult.success(picService.list())

    @GetMapping("/:id")
    fun getById(@RequestParam("id") id: Long): CommonResult<Pic> =
        CommonResult.success(picService.getById(id))

    @PostMapping("/")
    fun add(@RequestBody pic: Pic): CommonResult<Boolean> =
        CommonResult.success(picService.save(pic))

    @DeleteMapping("/:id")
    fun delById(@RequestParam("id") id: Long): CommonResult<Boolean> =
        CommonResult.success(picService.removeById(id))

    @PutMapping("/:id")
    fun updateById(@RequestBody pic: Pic): CommonResult<Boolean> =
        CommonResult.success(picService.updateById(pic))
}
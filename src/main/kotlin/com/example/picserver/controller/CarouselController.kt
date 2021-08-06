package com.example.picserver.controller

import com.example.picserver.common.CommonResult
import com.example.picserver.entity.Carousel
import com.example.picserver.entity.Pic
import com.example.picserver.service.CarouselService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@RequestMapping("carousel")
class CarouselController(@Resource private val carouselService: CarouselService) {
    @ApiOperation("获取轮播图")
    @GetMapping("")
    fun list() = CommonResult.success(carouselService.list())

    @GetMapping("/:id")
    fun getById(@RequestParam("id") id: Long): CommonResult<Carousel> =
        CommonResult.success(carouselService.getById(id))

    @PostMapping("/")
    fun add(@RequestBody carousel: Carousel): CommonResult<Boolean> =
        CommonResult.success(carouselService.save(carousel))

    @DeleteMapping("/:id")
    fun delById(@RequestParam("id") id: Long): CommonResult<Boolean> =
        CommonResult.success(carouselService.removeById(id))

    @PutMapping("/:id")
    fun updateById(@RequestBody carousel: Carousel): CommonResult<Boolean> =
        CommonResult.success(carouselService.updateById(carousel))
}
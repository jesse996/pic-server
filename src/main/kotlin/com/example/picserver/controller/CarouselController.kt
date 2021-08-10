package com.example.picserver.controller

import com.example.picserver.common.CommonResult
import com.example.picserver.entity.Carousel
import com.example.picserver.service.CarouselService
import io.swagger.annotations.ApiOperation
import org.apache.ibatis.ognl.DynamicSubscript.last
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource

@RequestMapping("carousel")
@RestController
class CarouselController(@Resource private val carouselService: CarouselService) {
    @ApiOperation("获取轮播图")
    @Cacheable
    @GetMapping("")
    fun list(@RequestParam(value = "count") count: Int?) =
        CommonResult.success(carouselService.ktQuery().last("limit ${count?:4}").list())

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long): CommonResult<Carousel> =
        CommonResult.success(carouselService.getById(id))

    @PostMapping("/")
    fun add(@RequestBody carousel: Carousel): CommonResult<Boolean> =
        CommonResult.success(carouselService.save(carousel))

    @DeleteMapping("/{id}")
    fun delById(@PathVariable("id") id: Long): CommonResult<Boolean> =
        CommonResult.success(carouselService.removeById(id))

    @PutMapping("/")
    fun updateById(@RequestBody carousel: Carousel): CommonResult<Boolean> =
        CommonResult.success(carouselService.updateById(carousel))
}
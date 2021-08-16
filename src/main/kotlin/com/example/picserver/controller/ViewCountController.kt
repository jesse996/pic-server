package com.example.picserver.controller

import com.example.picserver.common.CommonResult
import com.example.picserver.entity.ViewCount
import com.example.picserver.service.ViewCountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("viewCount")
class ViewCountController(val viewCountService: ViewCountService) {
    @GetMapping("pic")
    fun getByPicId(@RequestParam id:Long) =
        CommonResult.success( viewCountService.getByPicId(id))

    @GetMapping("news")
    fun getByNewsId(@RequestParam id:Long) =
        CommonResult.success( viewCountService.getByNewsId(id))
}
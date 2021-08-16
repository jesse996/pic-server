package com.example.picserver.controller;


import com.example.picserver.common.CommonResult
import com.example.picserver.entity.vo.LikeReq
import com.example.picserver.service.SysLikeService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

/**
 * <p>
 * 点赞表 前端控制器
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/like")
class SysLikeController(val sysLikeService: SysLikeService) {
    @PostMapping("")
    fun like(@RequestBody likeReq: LikeReq) =
        CommonResult.success(sysLikeService.like(likeReq))

    @ApiOperation("获取点赞数")
    @PostMapping("count")
    fun getLikeCount(@RequestBody likeReq: LikeReq)=
        CommonResult.success(sysLikeService.getLikeCount(likeReq))
}


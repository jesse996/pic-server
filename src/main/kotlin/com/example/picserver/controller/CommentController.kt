package com.example.picserver.controller;


import com.example.picserver.common.CommonResult
import com.example.picserver.entity.Comment
import com.example.picserver.entity.vo.CommentReq
import com.example.picserver.service.CommentService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/comment")
class CommentController(val commentService: CommentService) {
    @ApiOperation("添加评论")
    @PostMapping("")
    fun commentNews(@RequestBody comment: CommentReq) =
        CommonResult.success(commentService.addComment(comment))

}


package com.example.picserver.service;

import com.example.picserver.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.picserver.entity.vo.CommentReq
import com.example.picserver.entity.vo.CommentResp

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
interface CommentService : IService<Comment> {
    fun addComment(comment: CommentReq): Boolean
    fun getComment(objId: Long, type: Int): List<CommentResp>
}

package com.example.picserver.mapper;

import com.example.picserver.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.picserver.entity.vo.CommentResp

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
interface CommentMapper : BaseMapper<Comment> {
    fun getComment(objId: Long, type: Int): List<CommentResp>
}

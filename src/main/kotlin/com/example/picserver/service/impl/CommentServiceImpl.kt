package com.example.picserver.service.impl;

import cn.hutool.core.bean.BeanUtil
import com.example.picserver.entity.Comment;
import com.example.picserver.mapper.CommentMapper;
import com.example.picserver.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.entity.vo.CommentReq
import com.example.picserver.entity.vo.CommentResp
import com.example.picserver.security.MyUserDetails
import com.example.picserver.service.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@Service
open class CommentServiceImpl(val userService: UserService, val commentMapper: CommentMapper) :
    ServiceImpl<CommentMapper, Comment>(), CommentService {
    override fun addComment(comment: CommentReq): Boolean {
        val user = userService.current()!!
        val cmt = Comment()
        println(comment)
        BeanUtil.copyProperties(comment, cmt)
        cmt.userId = user.id
        return this.save(cmt)
    }

    override fun getComment(objId: Long, type: Int): List<CommentResp> {
//        val list = this.ktQuery().eq(Comment::type, type).eq(Comment::objId, objId).list()
        return commentMapper.getComment(objId, type)
    }
}

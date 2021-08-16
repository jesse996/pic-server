package com.example.picserver.service.impl;

import com.example.picserver.entity.Comment;
import com.example.picserver.mapper.CommentMapper;
import com.example.picserver.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
open class CommentServiceImpl : ServiceImpl<CommentMapper, Comment>(), CommentService {

}

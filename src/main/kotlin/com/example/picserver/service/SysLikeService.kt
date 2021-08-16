package com.example.picserver.service;

import com.example.picserver.entity.SysLike;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.picserver.entity.vo.LikeReq

/**
 * <p>
 * 点赞表 服务类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
interface SysLikeService : IService<SysLike> {
    fun like(likeReq: LikeReq): Boolean
    fun getLikeCount(likeReq: LikeReq): Int
}

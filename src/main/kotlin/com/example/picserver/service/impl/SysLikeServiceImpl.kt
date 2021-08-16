package com.example.picserver.service.impl;

import com.example.picserver.entity.SysLike;
import com.example.picserver.mapper.SysLikeMapper;
import com.example.picserver.service.SysLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.const.LikeEnum
import com.example.picserver.entity.vo.LikeReq
import com.example.picserver.service.UserService
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@Service
open class SysLikeServiceImpl(val userService: UserService) : ServiceImpl<SysLikeMapper, SysLike>(), SysLikeService {
    override fun like(likeReq: LikeReq): Boolean {
        val like = this.ktQuery()
            .eq(SysLike::type, likeReq.type)
            .eq(SysLike::objId, likeReq.objId)
            .one()
        if (like == null) {
            val tmp = SysLike()
            tmp.type = LikeEnum.PIC.code
            tmp.objId = likeReq.objId
            return this.save(tmp)
        }
        return false
    }

    override fun getLikeCount(likeReq: LikeReq):Int =
        this.ktQuery()
            .eq(SysLike::type, likeReq.type)
            .eq(SysLike::objId, likeReq.objId)
            .count()

}

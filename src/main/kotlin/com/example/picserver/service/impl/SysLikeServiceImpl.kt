package com.example.picserver.service.impl;

import com.example.picserver.entity.SysLike;
import com.example.picserver.mapper.SysLikeMapper;
import com.example.picserver.service.SysLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
open class SysLikeServiceImpl : ServiceImpl<SysLikeMapper, SysLike>(), SysLikeService {

}

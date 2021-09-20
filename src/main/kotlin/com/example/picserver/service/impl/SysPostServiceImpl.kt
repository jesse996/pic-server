package com.example.picserver.service.impl;

import cn.hutool.core.bean.BeanUtil
import com.baomidou.mybatisplus.core.metadata.IPage
import com.example.picserver.entity.SysPost;
import com.example.picserver.mapper.SysPostMapper;
import com.example.picserver.service.SysPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.entity.vo.PostResp
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-09-20
 */
@Service
open class SysPostServiceImpl : ServiceImpl<SysPostMapper, SysPost>(), SysPostService {

}

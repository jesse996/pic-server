package com.example.picserver.service.impl;

import com.example.picserver.entity.PicTag;
import com.example.picserver.mapper.PicTagMapper;
import com.example.picserver.service.PicTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * pic_tag表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-09
 */
@Service
open class PicTagServiceImpl : ServiceImpl<PicTagMapper, PicTag>(), PicTagService {

}

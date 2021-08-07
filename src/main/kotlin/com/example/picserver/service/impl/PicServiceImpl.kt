package com.example.picserver.service.impl;

import com.example.picserver.entity.Pic;
import com.example.picserver.mapper.PicMapper;
import com.example.picserver.service.PicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-07
 */
@Service
open class PicServiceImpl : ServiceImpl<PicMapper, Pic>(), PicService {

}

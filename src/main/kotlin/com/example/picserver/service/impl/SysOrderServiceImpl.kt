package com.example.picserver.service.impl;

import com.example.picserver.entity.SysOrder;
import com.example.picserver.mapper.SysOrderMapper;
import com.example.picserver.service.SysOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-21
 */
@Service
open class SysOrderServiceImpl : ServiceImpl<SysOrderMapper, SysOrder>(), SysOrderService {

}

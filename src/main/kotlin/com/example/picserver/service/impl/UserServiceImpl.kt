package com.example.picserver.service.impl;

import com.example.picserver.entity.User;
import com.example.picserver.mapper.UserMapper;
import com.example.picserver.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@Service
open class UserServiceImpl : ServiceImpl<UserMapper, User>(), UserService {

}

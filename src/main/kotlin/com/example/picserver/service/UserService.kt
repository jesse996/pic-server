package com.example.picserver.service;

import com.example.picserver.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.picserver.entity.vo.UserSignInReq
import com.example.picserver.entity.vo.UserSignUpReq

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
interface UserService : IService<User> {
    fun signIn(user: UserSignInReq): String
    fun signUp(user: UserSignUpReq): Boolean
    fun getByUsername(name:String):User?
    fun current(): User?
    fun logout(): Boolean
}

package com.example.picserver.service.impl;

import cn.dev33.satoken.stp.StpUtil
import cn.hutool.crypto.digest.BCrypt
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.example.picserver.entity.User
import com.example.picserver.entity.vo.UserSignInReq
import com.example.picserver.entity.vo.UserSignUpReq
import com.example.picserver.mapper.UserMapper
import com.example.picserver.service.UserService
import org.springframework.stereotype.Service


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
//    private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun signIn(user: UserSignInReq): String {
        val reply = this.getByUsername(user.username) ?: throw RuntimeException("用户不存在")
        if (!BCrypt.checkpw(user.password, reply.password)) {
            throw RuntimeException("密码不正确")
        }
        StpUtil.login(reply.id)
        val tokenInfo = StpUtil.getTokenInfo()
        return tokenInfo.tokenValue
    }

    override fun signUp(user: UserSignUpReq): Boolean {
        val sysUser = this.getByUsername(user.username)
        if (sysUser != null) {
            throw RuntimeException("用户名已被注册")
        }
        val tmp = User()
        tmp.username = user.username
        tmp.nickname = user.nickname
        val encode = BCrypt.hashpw(user.password)
        tmp.password = encode

        return this.save(tmp)
    }

    override fun getByUsername(name: String): User? =
        this.ktQuery()
            .eq(User::username, name)
            .one()

    override fun current(): User? {
        val userId = StpUtil.getLoginIdAsLong()
        return this.getById(userId)
    }
}

package com.example.picserver.service.impl;

import cn.dev33.satoken.stp.StpUtil
import cn.hutool.core.codec.Base64
import cn.hutool.core.lang.UUID
import cn.hutool.crypto.SecureUtil
import cn.hutool.crypto.digest.BCrypt
import cn.hutool.crypto.digest.DigestUtil
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.example.picserver.const.UserStatusEnum
import com.example.picserver.entity.User
import com.example.picserver.entity.vo.UserSignInReq
import com.example.picserver.entity.vo.UserSignUpReq
import com.example.picserver.mapper.UserMapper
import com.example.picserver.service.MailService
import com.example.picserver.service.UserService
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import java.util.concurrent.TimeUnit


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@Service
open class UserServiceImpl(val mailService: MailService, val redisTemplate: StringRedisTemplate) :
    ServiceImpl<UserMapper, User>(), UserService {
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

    @Transactional
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
        this.save(tmp)

        val token = UUID.fastUUID().toString(true)
        redisTemplate.opsForValue()
            .set(token, tmp.id!!.toString())
        redisTemplate.expire(token, 5, TimeUnit.MINUTES)

        //发送激活邮件
        mailService.sendEnableMail(tmp.username!!,Base64.encode(tmp.id!!.toString()))

        return true
    }

    override fun getByUsername(name: String): User? =
        this.ktQuery()
            .eq(User::username, name)
            .one()

    override fun current(): User? {
        if (!StpUtil.isLogin()) return null
        val userId = StpUtil.getLoginIdAsLong()
        return this.getById(userId)
    }

    override fun logout(): Boolean {
        StpUtil.logout()
        return true
    }

    override fun enable(encode: String): Boolean {
        val id = redisTemplate.opsForValue()
            .get(encode) ?: return false
        val user: User = this.getById(id.toLong()) ?: return false
        user.status = UserStatusEnum.VALID.code
        return this.updateById(user)
    }
}

package com.example.picserver.service.impl;

import cn.dev33.satoken.stp.StpUtil
import cn.hutool.core.codec.Base64
import cn.hutool.core.lang.UUID
import cn.hutool.core.util.RandomUtil
import cn.hutool.crypto.SecureUtil
import cn.hutool.crypto.digest.BCrypt
import cn.hutool.crypto.digest.DigestUtil
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.example.picserver.common.BizError
import com.example.picserver.const.UserStatusEnum
import com.example.picserver.entity.User
import com.example.picserver.entity.vo.UserSignInReq
import com.example.picserver.entity.vo.UserSignUpReq
import com.example.picserver.mapper.UserMapper
import com.example.picserver.service.MailService
import com.example.picserver.service.UserService
import mu.KotlinLogging
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import java.time.Duration
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
    private val logger = KotlinLogging.logger {}

    override fun signIn(user: UserSignInReq): String {
        val reply = this.getByUsername(user.username) ?: throw RuntimeException("用户名密码错误")
        if (!BCrypt.checkpw(user.password, reply.password)) {
            throw RuntimeException("用户名密码错误")
        }
        StpUtil.login(reply.id)
        val tokenInfo = StpUtil.getTokenInfo()
        return tokenInfo.tokenValue
    }

    @Transactional
    override fun signUp(user: UserSignUpReq): Boolean {
        val sysUser = this.getByUsername(user.username)
        if (sysUser != null) {
            throw BizError("用户名已被注册")
        }

        //验证
        val codeInRedis = redisTemplate.opsForValue().get(user.username) ?: throw BizError("验证码错误")
        if (codeInRedis != user.code) {
            throw BizError("验证码错误")
        }

        val tmp = User()
        tmp.username = user.username
        tmp.nickname = user.nickname
        val encode = BCrypt.hashpw(user.password)
        tmp.password = encode
        tmp.status = 1
        this.save(tmp)

        //发送激活邮件
//        val token = UUID.fastUUID().toString(true)
//        redisTemplate.opsForValue()
//            .set(token, tmp.id!!.toString())
//        redisTemplate.expire(token, 5, TimeUnit.MINUTES)
//
//
//        mailService.sendEnableMail(tmp.username!!, Base64.encode(tmp.id!!.toString()))

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

    /**
     * 注册发送验证码
     */
    override fun sendSignUpCode(email: String): Boolean {
        if (this.getByUsername(email) != null) {
            throw BizError("邮箱已注册！")
        }

        val code = RandomUtil.randomInts(4).joinToString(separator = "")
        val content = """
            欢迎注册，您的验证码是：$code 
            （15分钟内有效期。）
        """.trimIndent()
        redisTemplate.opsForValue().set(email, code)
        redisTemplate.expire(email, Duration.ofMinutes(15))
        mailService.sendMail(email, "欢迎注册妹子图", content)
        return true
    }
}

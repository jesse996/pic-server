package com.example.picserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.example.picserver.entity.User
import com.example.picserver.entity.vo.UserSignInReq
import com.example.picserver.entity.vo.UserSignUpReq
import com.example.picserver.mapper.UserMapper
import com.example.picserver.security.JwtTokenUtil
import com.example.picserver.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.annotation.Resource


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@Service
open class UserServiceImpl(
//    val userDetailsService: UserDetailsService,
//    val passwordEncoder: PasswordEncoder,
    val jwtTokenUtil: JwtTokenUtil
) : ServiceImpl<UserMapper, User>(), UserService {
    @Resource
    lateinit var userDetailsService: UserDetailsService

    @Resource
    lateinit var passwordEncoder: PasswordEncoder

//    private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun signIn(user: UserSignInReq): String {
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(user.username)
        if (!passwordEncoder.matches(user.password, userDetails.password)) {
            throw BadCredentialsException("密码不正确")
        }
        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        SecurityContextHolder.getContext().authentication = authentication
        return jwtTokenUtil.generateToken(userDetails)
    }

    override fun signUp(user: UserSignUpReq): Boolean {
        val sysUser = this.getByUsername(user.username)
        if (sysUser != null) {
            return false
        }
        val tmp = User()
        tmp.username = user.username
        tmp.nickname = user.nickname

        val encode = passwordEncoder.encode(user.password)
        tmp.password = encode
        println(encode)
        return this.save(tmp)
    }

    override fun getByUsername(name: String): User? =
        this.ktQuery()
            .eq(User::username, name)
            .one()
}

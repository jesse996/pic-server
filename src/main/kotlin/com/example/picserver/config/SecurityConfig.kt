package com.example.picserver.config

import com.example.picserver.security.JwtAuthenticationTokenFilter
import com.example.picserver.security.MyUserDetails
import com.example.picserver.security.RestAuthenticationEntryPoint
import com.example.picserver.security.RestfulAccessDeniedHandler
import com.example.picserver.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.annotation.Resource


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    val restfulAccessDeniedHandler: RestfulAccessDeniedHandler,
    val restAuthenticationEntryPoint: RestAuthenticationEntryPoint,
) : WebSecurityConfigurerAdapter() {
    @Resource
    lateinit var userService: UserService

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.csrf() // 由于使用的是JWT，我们这里不需要csrf
            .disable()
            .sessionManagement() // 基于token，所以不需要session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(
                HttpMethod.GET,  // 允许对于网站静态资源的无授权访问
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html#*",
                "/**/*.css",
                "/**/*.js",
                "/swagger-resources/**",
                "/v3/api-docs",
            )
            .permitAll()
            .antMatchers("/user/signIn", "/user/signUp") // 对登录注册要允许匿名访问
            .permitAll()
            .antMatchers(HttpMethod.OPTIONS) //跨域请求会先进行一次options请求
            .permitAll()
            .antMatchers(
                "/like/**",
                "/comment/**"
            )
            .authenticated()
        // 禁用缓存
        httpSecurity.headers().cacheControl()
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
            .accessDeniedHandler(restfulAccessDeniedHandler)
            .authenticationEntryPoint(restAuthenticationEntryPoint)
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    public override fun userDetailsService(): UserDetailsService {
        //获取登录用户信息
        return UserDetailsService { username: String ->
            val user = userService.getByUsername(username)
            if (user != null) {
                val permissionList = listOf("user")
                return@UserDetailsService MyUserDetails(user, permissionList)
            }
            throw UsernameNotFoundException("用户名或密码错误")
        }
    }

    @Bean
    fun jwtAuthenticationTokenFilter(): JwtAuthenticationTokenFilter {
        return JwtAuthenticationTokenFilter()
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}
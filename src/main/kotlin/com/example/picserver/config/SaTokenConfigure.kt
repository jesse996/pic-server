package com.example.picserver.config

import cn.dev33.satoken.context.model.SaRequest
import cn.dev33.satoken.context.model.SaResponse
import cn.dev33.satoken.interceptor.SaRouteInterceptor
import cn.dev33.satoken.router.SaHttpMethod
import cn.dev33.satoken.router.SaRouter
import cn.dev33.satoken.stp.StpUtil
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*


@Configuration
class SaTokenConfigure : WebMvcConfigurer {
    // 注册拦截器
    override fun addInterceptors(registry: InterceptorRegistry) {
        // 注册Sa-Token的路由拦截器，并排除登录接口或其他可匿名访问的接口地址 (与注解拦截器无关)
        registry.addInterceptor(SaRouteInterceptor { req: SaRequest, _: SaResponse?, _: Any? ->
            // 检查请求方式
            SaRouter.match(SaHttpMethod.POST)
                .match("/comment/**")
                .check { _ ->
                    StpUtil.checkLogin()
                }
//            SaRouter.match("/comment/**").check { _ ->
//                if (req.method.equals(HttpMethod.POST.toString())) {
//                    StpUtil.checkLogin()
//                }
//            }
        }).addPathPatterns("/**")

    }
}
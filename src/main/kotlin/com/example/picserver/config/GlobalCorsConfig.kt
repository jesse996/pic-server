package com.example.picserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

/**
 * Created by jesse on 2021/6/2 上午7:40
 */
@Configuration
class GlobalCorsConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val config = CorsConfiguration()
        //允许所有域名进行跨域调用
        config.addAllowedOriginPattern("*")
        //允许跨越发送cookie
        config.allowCredentials = true
        //放行全部原始头信息
        config.addAllowedHeader("*")
        //允许所有请求方法跨域调用
        config.addAllowedMethod("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}
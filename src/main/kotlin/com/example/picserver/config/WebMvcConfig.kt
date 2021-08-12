package com.example.picserver.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

//在caddy、nginx中设置静态
//@Configuration
class WebMvcConfig : WebMvcConfigurer {
    /**
     * Add handlers to serve static resources such as images, js, and, css
     * files from specific locations under web application root, the classpath,
     * and others.
     * @see ResourceHandlerRegistry
     */
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:C:/Users/jesse/Desktop/pic/spider/image/")
        super.addResourceHandlers(registry)
    }
}
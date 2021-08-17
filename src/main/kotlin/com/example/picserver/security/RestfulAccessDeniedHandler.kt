package com.example.picserver.security

import cn.hutool.json.JSONUtil
import com.example.picserver.common.CommonResult
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * 当访问接口没有权限时，自定义的返回结果
 * Created by macro on 2018/4/26.
 */
@Component
class RestfulAccessDeniedHandler : AccessDeniedHandler {
    @Throws(IOException::class, ServletException::class)
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException?
    ) {
        response.characterEncoding = "UTF-8"
        response.contentType = "application/json"
        response.writer?.println(JSONUtil.parse(CommonResult.fail(accessDeniedException?.message!!)))
        response.writer?.flush()
    }
}
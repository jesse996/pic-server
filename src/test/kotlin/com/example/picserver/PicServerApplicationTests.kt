package com.example.picserver

import cn.hutool.core.bean.BeanUtil
import cn.hutool.http.HttpUtil
import cn.hutool.json.JSONUtil
import com.example.picserver.entity.vo.VodClass
import com.example.picserver.entity.vo.VodResp
import com.example.picserver.service.MailService
import com.example.picserver.service.SysVodDetailService
import com.example.picserver.service.SysVodService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import javax.annotation.Resource

@SpringBootTest
class PicServerApplicationTests {
    @Resource
    lateinit var mailService: MailService
    @Resource
    lateinit var templateEngine: TemplateEngine
    @Resource
    lateinit var sysVodService: SysVodService
    @Resource
    lateinit var sysVodDetailService: SysVodDetailService

    @Test
    fun contextLoads() {
    }

    @Test
    fun sendMail() {
        //创建邮件正文
        val context = Context()
        context.setVariable("id", "006")
        val emailContent: String = templateEngine.process("emailTemplate", context)
        mailService.sendMail("951576941@qq.com", "test", emailContent)
    }

    @Test
    fun getVod(){
        sysVodService.spiderAll()
    }

    @Test
    fun getVodDetail(){
        sysVodDetailService.spiderDetail()
    }

}

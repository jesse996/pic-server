package com.example.picserver

import com.example.picserver.service.MailService
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
}

package com.example.picserver.service.impl

import com.example.picserver.service.MailService
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import javax.mail.MessagingException

@Service
class MailServiceImpl(val javaMailSender: JavaMailSender,val templateEngine: TemplateEngine) : MailService {
    @Value("\${spring.mail.username}")
    lateinit var from: String


    override fun sendMail(to: String, subject: String, content: String): Boolean {
        val message = SimpleMailMessage()
        message.setFrom(from)
        message.setTo(to)
        message.setSubject(subject)
        message.setText(content)

        return try {
            javaMailSender.send(message)
            true
        } catch (e: MessagingException) {
            println(e)
            false
        }
    }

    override fun sendHtmlMail(to: String, subject: String, content: String): Boolean {
        val message = javaMailSender.createMimeMessage()
        return try {
            val helper = MimeMessageHelper(message)
            helper.setFrom(from)
            helper.setTo(to)
            helper.setSubject(subject)
            helper.setText(content, true)

            javaMailSender.send(message)
            true
        } catch (e: MessagingException) {
            println(e)
            false
        }
    }

    override fun sendEnableMail(to: String, id: String) {
        //创建邮件正文
        val context = Context()
        context.setVariable("id", id)
        val emailContent: String = templateEngine.process("emailTemplate", context)

        this.sendHtmlMail(to,"欢迎注册，请激活",emailContent)
    }
}
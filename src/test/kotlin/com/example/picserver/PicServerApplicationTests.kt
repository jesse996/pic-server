package com.example.picserver

import cn.hutool.core.bean.BeanUtil
import cn.hutool.http.HttpUtil
import cn.hutool.json.JSONUtil
import com.example.picserver.entity.SysVod
import com.example.picserver.entity.SysVodClass
import com.example.picserver.entity.vo.VodClass
import com.example.picserver.entity.vo.VodResp
import com.example.picserver.service.MailService
import com.example.picserver.service.SysVodClassService
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

    @Resource
    lateinit var sysVodClassService: SysVodClassService

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
    fun getVod() {
        sysVodService.spiderAll()
    }

    @Test
    fun getVodDetail() {
        sysVodDetailService.spiderDetail()
    }

    @Test
    fun setVodClass() {
        val res: String = HttpUtil.get("https://api.apibdzy.com/api.php/provide/vod/?ac=list")

        val parse = JSONUtil.parseObj(res)
        val list = parse.getJSONArray("class").toList(VodClass::class.java)
        val list1 = list.map {
            val res1 = SysVodClass()
            BeanUtil.copyProperties(it, res1)
            res1.typeId=it.type_id
            res1.typeName = it.type_name

            println(it)
            println(res1)
            res1
        }.toList()
        sysVodClassService.saveOrUpdateBatch(list1)
    }
}

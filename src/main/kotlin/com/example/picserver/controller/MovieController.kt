package com.example.picserver.controller

import cn.hutool.http.HttpUtil
import com.example.picserver.entity.SysVod
import com.example.picserver.service.SysVodDetailService
import com.example.picserver.service.SysVodService
import io.swagger.annotations.ApiOperation
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController(
    val sysVodService: SysVodService,
    val sysVodDetailService: SysVodDetailService
) {
    //每天的00：01 爬虫
    @Scheduled(cron = "0 7 0 * * *")
    @ApiOperation("爬取过去25小时")
    fun getNew() {
        sysVodService.spiderByHour(25)
        sysVodDetailService.spiderByHour(26)
    }
}
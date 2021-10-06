package com.example.picserver.controller

import cn.hutool.http.HttpUtil
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
    //    @Scheduled(cron = "0 1 * * * *")
    @ApiOperation("爬取全部")
    fun getNew() {
        sysVodService.spiderAll();
    }
}
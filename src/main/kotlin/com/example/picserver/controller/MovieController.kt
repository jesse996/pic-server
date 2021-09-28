package com.example.picserver.controller

import io.swagger.annotations.ApiOperation
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController {
//    @Scheduled(cron = "0 1 * * * *")
    @ApiOperation("爬取全部")
    fun getNew(){
        
    }
}
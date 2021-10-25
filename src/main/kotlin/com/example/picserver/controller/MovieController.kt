package com.example.picserver.controller

import com.example.picserver.common.CommonResult
import com.example.picserver.common.VodCommonResult
import com.example.picserver.service.SysVodDetailService
import com.example.picserver.service.SysVodService
import io.swagger.annotations.ApiOperation
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/provide/vod")
class MovieController(
    val sysVodService: SysVodService,
    val sysVodDetailService: SysVodDetailService
) {
    //每天的00：01 爬虫
    @Scheduled(cron = "0 7 0 * * *")
    @ApiOperation("爬取过去25小时")
    @PostMapping("/spider")
    fun getNew(@RequestBody(required = false) hours: Int?): CommonResult<Boolean> {
        sysVodService.spiderByHour(hours ?: 25)
        sysVodDetailService.spiderByHour(hours ?: 26)
        return CommonResult.success(true)
    }

    @ApiOperation("查询")
    @GetMapping("")
    fun get(
        @RequestParam("ac") ac: String,
        @RequestParam(value = "t", required = false) t: Long?,
        @RequestParam(value = "pg", defaultValue = "1") pg: Long,
        @RequestParam("wd", required = false) wd: String?,
        @RequestParam("h", required = false) h: Long?,
        @RequestParam("ids", required = false) ids: List<Long>?,
        @RequestParam("limit", required = false) limit: Long?,
    ) =
        when (ac) {
            "list" -> sysVodService.get(t, pg, wd, h, limit)
            "detail" -> sysVodDetailService.get(t, pg, wd, h, ids, limit)
            else -> VodCommonResult.success(null, "error")
        }

}
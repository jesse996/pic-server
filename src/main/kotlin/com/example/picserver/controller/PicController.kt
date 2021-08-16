package com.example.picserver.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.example.picserver.common.CommonResult
import com.example.picserver.entity.Pic
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.entity.vo.PicResp
import com.example.picserver.service.PicService
import io.swagger.annotations.ApiOperation
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*

@RestController
@CacheConfig(cacheNames = ["pic"])
@RequestMapping("pic")
class PicController(val picService: PicService) {
    @PostMapping("list")
    @Cacheable
    fun list(@RequestBody pageReq: PageReq<Pic>): CommonResult<IPage<PicResp>> =
        CommonResult.success(picService.pagePicResp(pageReq))

    @Cacheable(key = "#id")
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long) = CommonResult.success(picService.getPicRespById(id))

    @PostMapping("")
    fun add(@RequestBody picResp: PicResp): CommonResult<Boolean> {
        //根据封面图去重
        if (picService.ktQuery().eq(Pic::coverImg, picResp.coverImg).count() > 0) {
            return CommonResult.success(false)
        }
        return CommonResult.success(picService.savePic(picResp))
    }

    @CacheEvict(key = "#id")
    @DeleteMapping("/{id}")
    fun delById(@PathVariable("id") id: Long) = CommonResult.success(picService.removePic(id))

    @CachePut(key = "#picResp.id")
    @PutMapping("")
    fun updateById(@RequestBody picResp: PicResp) = CommonResult.success(picService.updatePic(picResp))

    @ApiOperation("更新图片时间")
    @GetMapping("updateTime")
    fun updateTime(): Boolean {
        val list = picService.ktQuery()
            .eq(Pic::src, "www.mzitu.com")
            .list()
        list.sortBy { it.createTime }
        var count = list.size.toLong()
        for (i in list) {
            i.createTime = i.createTime?.plusMinutes(count*5)
            i.updateTime = i.createTime
            count --
        }
        picService.updateBatchById(list)
        return true
    }
}
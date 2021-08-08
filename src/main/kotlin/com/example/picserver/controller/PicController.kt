package com.example.picserver.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.example.picserver.common.CommonResult
import com.example.picserver.entity.Pic
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.entity.vo.PicResp
import com.example.picserver.service.PicService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("pic")
class PicController(val picService: PicService) {
    @PostMapping("list")
    fun list(@RequestBody pageReq: PageReq<Pic>): CommonResult<IPage<PicResp>> =
        CommonResult.success(picService.pagePicResp(pageReq))


    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long) = CommonResult.success(picService.getPicRespById(id))

    @PostMapping("")
    fun add(@RequestBody picResp: PicResp): CommonResult<Boolean> {
        if (picService.ktQuery().eq(Pic::coverImg, picResp.coverImg).eq(Pic::type, picResp.type).count() > 0) {
            return CommonResult.success(false);
        }
        return CommonResult.success(picService.savePic(picResp))
    }

    @DeleteMapping("/{id}")
    fun delById(@PathVariable("id") id: Long) = CommonResult.success(picService.removePic(id))

    @PutMapping("")
    fun updateById(@RequestBody picResp: PicResp) = CommonResult.success(picService.updatePic(picResp))

}
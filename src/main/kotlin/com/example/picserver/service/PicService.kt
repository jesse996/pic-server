package com.example.picserver.service;

import com.baomidou.mybatisplus.core.metadata.IPage
import com.example.picserver.entity.Pic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.entity.vo.PicResp

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jesse
 * @since 2021-08-06
 */
interface PicService : IService<Pic> {
    fun pagePicResp(pageReq: PageReq<Pic>): IPage<PicResp>
    fun getPicRespById(id: Long): PicResp
    fun savePic(picResp: PicResp): Boolean
    fun removePic(id: Long): Boolean
    fun updatePic(picResp: PicResp): Boolean
}

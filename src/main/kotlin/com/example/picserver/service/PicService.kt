package com.example.picserver.service;

import com.example.picserver.entity.Pic;
import com.baomidou.mybatisplus.extension.service.IService;
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
    fun getPicResp(id: Long): PicResp
    fun savePicResp(picResp: PicResp): Boolean
}

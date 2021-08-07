package com.example.picserver.service.impl;

import cn.hutool.core.bean.BeanUtil
import com.example.picserver.entity.Pic;
import com.example.picserver.mapper.PicMapper;
import com.example.picserver.service.PicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.entity.vo.PicResp
import com.example.picserver.service.PicListService
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-06
 */
@Service
open class PicServiceImpl(val picListService: PicListService) : ServiceImpl<PicMapper, Pic>(), PicService {
    override fun getPicResp(id: Long): PicResp {
        val pic = this.getById(id)
        val picResp = BeanUtil.toBean(pic, PicResp::class.java)
        picResp.imgList = picListService.getById(pic.id).urlList
        return picResp
    }

}

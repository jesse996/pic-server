package com.example.picserver.service;

import com.example.picserver.entity.PicList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 图片列表详情 服务类
 * </p>
 *
 * @author jesse
 * @since 2021-08-07
 */
interface PicListService : IService<PicList> {
    fun getByPicId(id: Long?): PicList?
}

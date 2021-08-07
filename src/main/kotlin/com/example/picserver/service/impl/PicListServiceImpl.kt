package com.example.picserver.service.impl;

import com.example.picserver.entity.PicList;
import com.example.picserver.mapper.PicListMapper;
import com.example.picserver.service.PicListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图片列表详情 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-07
 */
@Service
open class PicListServiceImpl : ServiceImpl<PicListMapper, PicList>(), PicListService {
    override fun getByPicId(id: Long?): PicList?=
        this.ktQuery().eq(PicList::picId,id).one()
}

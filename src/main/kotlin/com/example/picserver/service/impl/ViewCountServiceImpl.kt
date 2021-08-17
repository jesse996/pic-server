package com.example.picserver.service.impl;

import com.example.picserver.entity.ViewCount;
import com.example.picserver.mapper.ViewCountMapper;
import com.example.picserver.service.ViewCountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.const.ViewCountEnum
import org.springframework.stereotype.Service;

/**
 * <p>
 * 浏览量表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@Service
open class ViewCountServiceImpl : ServiceImpl<ViewCountMapper, ViewCount>(), ViewCountService {
    override fun increase(id: Long, type: Int) {
        var viewCount = this.ktQuery()
            .eq(ViewCount::type, type)
            .eq(ViewCount::targetId, id)
            .one()
        if (viewCount == null) {
            val tmp = ViewCount()
            tmp.targetId = id
            tmp.type = type
            tmp.count = 0
            this.save(tmp)
            viewCount = tmp
        }

        viewCount.count = (viewCount.count ?: 0) + 1
        this.updateById(viewCount)
    }

    override fun getByPicId(id: Long): ViewCount? =
        this.ktQuery().eq(ViewCount::targetId, id).eq(ViewCount::type, ViewCountEnum.PIC.code).one()

    override fun getByNewsId(id: Long): ViewCount? =
        this.ktQuery().eq(ViewCount::targetId, id).eq(ViewCount::type, ViewCountEnum.NEWS.code).one()
}

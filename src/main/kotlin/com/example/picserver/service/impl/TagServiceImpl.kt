package com.example.picserver.service.impl;

import com.example.picserver.entity.Tag;
import com.example.picserver.mapper.TagMapper;
import com.example.picserver.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-09
 */
@Service
open class TagServiceImpl : ServiceImpl<TagMapper, Tag>(), TagService {
    override fun getByName(name: String): Tag? {
        return this.ktQuery()
            .eq(Tag::name, name)
            .one()
    }
}

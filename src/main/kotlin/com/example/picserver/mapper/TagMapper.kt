package com.example.picserver.mapper;

import com.example.picserver.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author jesse
 * @since 2021-08-09
 */
interface TagMapper : BaseMapper<Tag> {
    fun getByPicId(id: Long): List<Tag>
}

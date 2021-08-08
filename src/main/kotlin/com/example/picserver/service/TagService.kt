package com.example.picserver.service;

import com.example.picserver.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author jesse
 * @since 2021-08-09
 */
interface TagService : IService<Tag> {
    fun getByName(name: String): Tag?
}

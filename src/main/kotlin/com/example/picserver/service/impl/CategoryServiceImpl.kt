package com.example.picserver.service.impl;

import com.example.picserver.entity.Category;
import com.example.picserver.mapper.CategoryMapper;
import com.example.picserver.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 类别表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@Service
open class CategoryServiceImpl : ServiceImpl<CategoryMapper, Category>(), CategoryService {
    override fun getByName(category: String): Category {
        var res = this.ktQuery()
            .eq(Category::name, category)
            .one()
        if (res == null) {
            val tmp = Category()
            tmp.name = category
            this.save(tmp)
            res = tmp
        }
        return res
    }
}

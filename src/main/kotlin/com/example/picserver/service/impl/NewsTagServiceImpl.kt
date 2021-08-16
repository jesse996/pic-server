package com.example.picserver.service.impl;

import com.example.picserver.entity.NewsTag;
import com.example.picserver.mapper.NewsTagMapper;
import com.example.picserver.service.NewsTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@Service
open class NewsTagServiceImpl : ServiceImpl<NewsTagMapper, NewsTag>(), NewsTagService {

}

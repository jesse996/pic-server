package com.example.picserver.service.impl;

import com.example.picserver.entity.News;
import com.example.picserver.mapper.NewsMapper;
import com.example.picserver.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 轮播图表 服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-06
 */
@Service
open class NewsServiceImpl : ServiceImpl<NewsMapper, News>(), NewsService {

}

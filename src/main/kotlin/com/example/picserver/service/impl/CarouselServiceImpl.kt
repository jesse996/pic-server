package com.example.picserver.service.impl;

import com.example.picserver.entity.Carousel;
import com.example.picserver.mapper.CarouselMapper;
import com.example.picserver.service.CarouselService;
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
open class CarouselServiceImpl : ServiceImpl<CarouselMapper, Carousel>(), CarouselService {

}

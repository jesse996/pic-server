package com.example.picserver.service.impl;

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.collection.CollUtil
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.example.picserver.entity.Pic;
import com.example.picserver.mapper.PicMapper;
import com.example.picserver.service.PicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.picserver.entity.PicTag
import com.example.picserver.entity.Tag
import com.example.picserver.entity.vo.PageReq
import com.example.picserver.entity.vo.PicResp
import com.example.picserver.mapper.TagMapper
import com.example.picserver.service.PicTagService
import com.example.picserver.service.TagService
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesse
 * @since 2021-08-07
 */
@Service
open class PicServiceImpl(val tagMapper: TagMapper, val tagService: TagService, val picTagService: PicTagService) :
    ServiceImpl<PicMapper, Pic>(),
    PicService {
//    @Resource
//    lateinit var tagMapper: TagMapper

    override fun pagePicResp(pageReq: PageReq<Pic>): IPage<PicResp> {
        val page = Page<Pic>(pageReq.current, pageReq.size)
        val wrapper = LambdaQueryWrapper<Pic>(pageReq.data)
        return this.page(page, wrapper).convert { getPicRespById(it.id!!) }
    }

    override fun getPicRespById(id: Long): PicResp {
        val pic = this.getById(id)
        val picResp = BeanUtil.toBean(pic, PicResp::class.java)
        picResp.tags = tagMapper.getByPicId(id)
        return picResp
    }

    @Transactional
    override fun savePic(picResp: PicResp): Boolean {
        this.save(picResp)
        addPicTag(picResp)
        return true
    }

    @Transactional
    override fun removePic(id: Long): Boolean {
        this.removeById(id)
        return picTagService.ktUpdate()
            .eq(PicTag::picId, id)
            .remove()
    }

    @Transactional
    override fun updatePic(picResp: PicResp): Boolean {
        this.updateById(picResp)
        picTagService.ktUpdate()
            .eq(PicTag::picId, picResp.id)
            .remove()

        addPicTag(picResp)
        return true
    }

    fun addPicTag(picResp: PicResp) {
        if (CollUtil.isNotEmpty(picResp.tags)) {

            val list = picResp.tags!!.map {
                //如果传的标签不存在，就用传的创建标签
                if (tagService.getById(it.id) == null) {
                    tagService.save(it)
                }

                val pt = PicTag()
                pt.tagId = it.id
                pt.picId = picResp.id
                pt
            }
            picTagService.saveBatch(list)
        }
    }
}

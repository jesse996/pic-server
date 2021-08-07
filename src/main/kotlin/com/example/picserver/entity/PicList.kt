package com.example.picserver.entity;

import com.baomidou.mybatisplus.annotation.*
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 图片列表详情
 * </p>
 *
 * @author jesse
 * @since 2021-08-07
 */
@ApiModel(value="PicList对象", description="图片列表详情")
@TableName(autoResultMap = true)
open class PicList : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    @ApiModelProperty(value = "pic表中id")
    var picId: Long? = null
    @ApiModelProperty(value = "图片url列表")
    @TableField(typeHandler = JacksonTypeHandler::class)
    var urlList: List<String>? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null
    var deleted: Int? = null


    override fun toString(): String {
        return "PicList{" +
        "id=" + id +
        ", picId=" + picId +
        ", urlList=" + urlList +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        "}"
    }
}

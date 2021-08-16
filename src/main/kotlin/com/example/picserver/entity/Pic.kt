package com.example.picserver.entity;

import com.baomidou.mybatisplus.annotation.*
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 
 * </p>
 *
 * @author jesse
 * @since 2021-08-07
 */
@ApiModel(value="Pic对象", description="")
@TableName(autoResultMap = true)
open class Pic : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    @ApiModelProperty(value = "封面图")
    var coverImg: String? = null
    @ApiModelProperty(value = "标题")
    var title: String? = null
    @ApiModelProperty(value = "描述")
    var description: String? = null
    @ApiModelProperty(value = "来源")
    var src: String? = null
    @ApiModelProperty(value = "0:性感妹子,1:二次元,2:cosplay,3:清纯妹子")
    var type: Int? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null
    @ApiModelProperty(value = "图片列表")
    @TableField(typeHandler = JacksonTypeHandler::class)
    var imgList: List<String>? = null


    override fun toString(): String {
        return "Pic{" +
        "id=" + id +
        ", coverImg=" + coverImg +
        ", title=" + title +
        ", description=" + description +
        ", src=" + src +
        ", type=" + type +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", imgList=" + imgList +
        "}"
    }
}

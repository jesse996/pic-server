package com.example.picserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 轮播图表
 * </p>
 *
 * @author jesse
 * @since 2021-08-07
 */
@ApiModel(value="News对象", description="轮播图表")
open class News : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    @ApiModelProperty(value = "封面图片url")
    var coverUrl: String? = null
    @ApiModelProperty(value = "标题")
    var title: String? = null
    @ApiModelProperty(value = "内容")
    var content: String? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null
    var deleted: Int? = null
    @ApiModelProperty(value = "描述")
    var description: String? = null


    override fun toString(): String {
        return "News{" +
        "id=" + id +
        ", coverUrl=" + coverUrl +
        ", title=" + title +
        ", content=" + content +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        ", description=" + description +
        "}"
    }
}

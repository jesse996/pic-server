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
 * 浏览量表
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@ApiModel(value="ViewCount对象", description="浏览量表")
open class ViewCount : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    @ApiModelProperty(value = "0:pic,1:文章")
    var type: Int? = null
    @ApiModelProperty(value = "pic id或news id")
    var targetId: Long? = null
    @ApiModelProperty(value = "浏览次数")
    var count: Int? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "ViewCount{" +
        "id=" + id +
        ", type=" + type +
        ", targetId=" + targetId +
        ", count=" + count +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}

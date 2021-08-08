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
 * 标签表
 * </p>
 *
 * @author jesse
 * @since 2021-08-09
 */
@ApiModel(value="Tag对象", description="标签表")
open class Tag : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    @ApiModelProperty(value = "tag名")
    var name: String? = null
    @ApiModelProperty(value = "封面图")
    var coverImg: String? = null
    @ApiModelProperty(value = "0:类别，1：专辑")
    var type: Int? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null
    var deleted: Int? = null


    override fun toString(): String {
        return "Tag{" +
        "id=" + id +
        ", name=" + name +
        ", coverImg=" + coverImg +
        ", type=" + type +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        "}"
    }
}

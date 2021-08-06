package com.example.picserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 
 * </p>
 *
 * @author jesse
 * @since 2021-08-06
 */
@ApiModel(value="Pic对象", description="")
class Pic : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    @ApiModelProperty(value = "图片url")
    var url: String? = null
    @ApiModelProperty(value = "标题")
    var title: String? = null
    @ApiModelProperty(value = "描述")
    var description: String? = null
    @ApiModelProperty(value = "来源")
    var src: String? = null
    @ApiModelProperty(value = "名字")
    var name: String? = null
    @ApiModelProperty(value = "0:三次元，1：二次元，2：cosplay")
    var type: Int? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "Pic{" +
        "id=" + id +
        ", url=" + url +
        ", title=" + title +
        ", description=" + description +
        ", src=" + src +
        ", name=" + name +
        ", type=" + type +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}

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
 * 点赞表
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@ApiModel(value="SysLike对象", description="点赞表")
open class SysLike : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    var userId: Long? = null
    var picId: Long? = null
    var newId: Long? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "SysLike{" +
        "id=" + id +
        ", userId=" + userId +
        ", picId=" + picId +
        ", newId=" + newId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}

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
 * 用户表
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@ApiModel(value="User对象", description="用户表")
open class User : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    @ApiModelProperty(value = "昵称")
    var nickname: String? = null
    @ApiModelProperty(value = "用户名（邮箱）")
    var username: String? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "User{" +
        "id=" + id +
        ", nickname=" + nickname +
        ", username=" + username +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}

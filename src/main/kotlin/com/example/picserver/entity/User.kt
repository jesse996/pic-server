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
 * @since 2021-09-08
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
    @ApiModelProperty(value = "密码")
    var password: String? = null
    @ApiModelProperty(value = "0:未激活，1：已激活，正常，2：冻结")
    var status: Int? = null
    @ApiModelProperty(value = "0:user,1:admin")
    var role: Int? = null
    @ApiModelProperty(value = "vip过期时间")
    var vipExpireTime: LocalDateTime? = null


    override fun toString(): String {
        return "User{" +
        "id=" + id +
        ", nickname=" + nickname +
        ", username=" + username +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", password=" + password +
        ", status=" + status +
        ", role=" + role +
        ", vipExpireTime=" + vipExpireTime +
        "}"
    }
}

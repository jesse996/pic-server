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
 * 
 * </p>
 *
 * @author jesse
 * @since 2021-09-08
 */
@ApiModel(value="SysOrder对象", description="")
open class SysOrder : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    @ApiModelProperty(value = "用户id，可为null，就是未登录")
    var userId: Long? = null
    @ApiModelProperty(value = "金额，单位分")
    var amount: Long? = null
    @ApiModelProperty(value = "目标id，为null时代表时充值vip")
    var targetId: Long? = null
    @ApiModelProperty(value = "0：赞赏作品，1：赞赏文章,2:买作品，3:充值Vip")
    var type: Int? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null
    @ApiModelProperty(value = "0:待支付，1：支付成功，2：支付失败")
    var status: Int? = null
    @ApiModelProperty(value = "type为3时代表充值月数")
    var extra: String? = null


    override fun toString(): String {
        return "SysOrder{" +
        "id=" + id +
        ", userId=" + userId +
        ", amount=" + amount +
        ", targetId=" + targetId +
        ", type=" + type +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", status=" + status +
        ", extra=" + extra +
        "}"
    }
}

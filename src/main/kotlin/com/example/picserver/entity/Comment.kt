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
 * 评论表
 * </p>
 *
 * @author jesse
 * @since 2021-08-17
 */
@ApiModel(value="Comment对象", description="评论表")
open class Comment : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    var userId: Long? = null
    var content: String? = null
    @ApiModelProperty(value = "回复的评论id，主评论为null")
    var toCommentId: Long? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null
    @ApiModelProperty(value = "0:news,1:pic")
    var type: Int? = null
    @ApiModelProperty(value = "评论对象id")
    var objId: Long? = null


    override fun toString(): String {
        return "Comment{" +
        "id=" + id +
        ", userId=" + userId +
        ", content=" + content +
        ", toCommentId=" + toCommentId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", type=" + type +
        ", objId=" + objId +
        "}"
    }
}

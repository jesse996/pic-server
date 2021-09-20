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
 * 文章表
 * </p>
 *
 * @author jesse
 * @since 2021-09-20
 */
@ApiModel(value="SysPost对象", description="文章表")
open class SysPost : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    @ApiModelProperty(value = "标题")
    var title: String? = null
    @ApiModelProperty(value = "文章对应的电影id")
    var vodId: Long? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "SysPost{" +
        "id=" + id +
        ", title=" + title +
        ", vodId=" + vodId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}

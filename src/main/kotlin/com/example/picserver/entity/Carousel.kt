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
 * @since 2021-08-06
 */
@ApiModel(value="Carousel对象", description="轮播图表")
class Carousel : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    @ApiModelProperty(value = "图片url")
    var url: String? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "Carousel{" +
        "id=" + id +
        ", url=" + url +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}

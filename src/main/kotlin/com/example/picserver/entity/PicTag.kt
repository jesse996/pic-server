package com.example.picserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * pic_tag表
 * </p>
 *
 * @author jesse
 * @since 2021-08-09
 */
@ApiModel(value="PicTag对象", description="pic_tag表")
open class PicTag : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    var picId: String? = null
    var tagId: String? = null


    override fun toString(): String {
        return "PicTag{" +
        "id=" + id +
        ", picId=" + picId +
        ", tagId=" + tagId +
        "}"
    }
}

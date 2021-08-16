package com.example.picserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 
 * </p>
 *
 * @author jesse
 * @since 2021-08-16
 */
@ApiModel(value="NewsTag对象", description="")
open class NewsTag : Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    var id: Long? = null
    var newsId: Long? = null
    var tagId: Long? = null


    override fun toString(): String {
        return "NewsTag{" +
        "id=" + id +
        ", newsId=" + newsId +
        ", tagId=" + tagId +
        "}"
    }
}

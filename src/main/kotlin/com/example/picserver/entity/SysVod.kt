package com.example.picserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
 * @since 2021-09-28
 */
@ApiModel(value="SysVod对象", description="")
open class SysVod : Serializable {

    var typeId: Int? = null
    var typeName: String? = null
    var vodEn: String? = null
    @TableId(value = "vod_id", type = IdType.INPUT)
    var vodId: Int? = null
    var vodName: String? = null
    var vodPlayFrom: String? = null
    var vodRemarks: String? = null
    var vodTime: LocalDateTime? = null


    override fun toString(): String {
        return "SysVod{" +
        "typeId=" + typeId +
        ", typeName=" + typeName +
        ", vodEn=" + vodEn +
        ", vodId=" + vodId +
        ", vodName=" + vodName +
        ", vodPlayFrom=" + vodPlayFrom +
        ", vodRemarks=" + vodRemarks +
        ", vodTime=" + vodTime +
        "}"
    }
}

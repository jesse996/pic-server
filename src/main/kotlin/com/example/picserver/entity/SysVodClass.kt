package com.example.picserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonKey
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 
 * </p>
 *
 * @author jesse
 * @since 2021-10-18
 */

@ApiModel(value="SysVodClass对象", description="")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
open class SysVodClass : Serializable {

    @TableId(value = "type_id", type = IdType.INPUT)
    @JsonKey
    var typeId: Int? = null
    var typeName: String? = null
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "SysVodClass{" +
        "typeId=" + typeId +
        ", typeName=" + typeName +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}

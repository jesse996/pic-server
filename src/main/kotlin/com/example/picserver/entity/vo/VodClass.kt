package com.example.picserver.entity.vo

class VodClass(
    var type_id: Int?,
    var type_name: String?
){
    override fun toString(): String {
        return "VodClass(type_id=$type_id, type_name='$type_name')"
    }
}
package com.example.picserver.const

enum class LikeEnum(val code: Int) {
    PIC(0),
    NEWS(1);

    override fun toString(): String {
        return "LikeEnum(code=$code)"
    }


}
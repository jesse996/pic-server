package com.example.picserver.entity.vo

data class CommentReq(
    val content: String,
    val type: Int,
    val objId: Long,
    val toCommentId: Long?
)
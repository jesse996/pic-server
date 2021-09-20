package com.example.picserver.entity.vo

import com.example.picserver.entity.SysPost

data class PostResp(
    val vodName: String,
    val vodPic: String
) : SysPost()

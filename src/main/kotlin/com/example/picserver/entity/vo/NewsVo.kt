package com.example.picserver.entity.vo

import com.example.picserver.entity.News

class NewsVo(
    var category: String?,
    var tags: List<String>?,
    var likeCount: Int?,
    var viewCount: Int?,
    var commentCount: Int?
) : News()
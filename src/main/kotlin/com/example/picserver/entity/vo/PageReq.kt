package com.example.picserver.entity.vo

data class PageReq<T>(val current: Long, val size: Long, val data: T?)
package com.example.picserver

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
@MapperScan("com.example.picserver.mapper")
class PicServerApplication

fun main(args: Array<String>) {
    runApplication<PicServerApplication>(*args)
}

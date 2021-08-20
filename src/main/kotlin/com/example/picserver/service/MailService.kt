package com.example.picserver.service

interface MailService {
    fun sendMail(to:String,subject:String,content:String):Boolean
    fun sendHtmlMail(to:String,subject:String,content:String):Boolean
}

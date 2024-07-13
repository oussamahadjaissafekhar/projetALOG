package com.example.myapplication.entity

data class LoginResponse(
    val ClientId: Int,
    val ClientName:String,
    val ClientPassword:String,
    val ClientEmail:String,
    val clientNumPhone:String,
    val ClientAdress:String
)

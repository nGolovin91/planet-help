package com.yoite.planethelp.data.repository


data class ErrorModel(
    val status : Int,
    val code : String,
    val message : String
)
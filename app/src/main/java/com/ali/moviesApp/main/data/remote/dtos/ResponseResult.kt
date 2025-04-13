package com.ali.moviesApp.main.data.remote.dtos
import com.google.gson.annotations.SerializedName

data class ResponseResult <T> (
    @SerializedName("result")
    val result:T?,
    @SerializedName("code")
    val code:Int,
    @SerializedName("message")
    val message :String
)


package com.ali.moviesApp.main.data.remote.dtos

import com.google.gson.annotations.SerializedName

sealed class EndPointResult<T> {
    data class Loading<T>(val isLoading:Boolean = true): EndPointResult<T>()
    data class Success<T>(val result:T): EndPointResult<T>()
    data class Error<T>(val code: Int=500,val message: String?): EndPointResult<T>()
}
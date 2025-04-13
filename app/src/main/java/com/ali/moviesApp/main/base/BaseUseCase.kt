package com.ali.moviesApp.main.base

import android.annotation.SuppressLint
import android.util.Log
import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

abstract class BaseUseCase<in P, R> {
    @SuppressLint("LogNotTimber")
    private suspend fun getResponse(params: P?): Flow<EndPointResult<R>> = flow {
        emit(EndPointResult.Loading())
        try {
            val data = getData(params)
            Log.e("Data : ", Gson().toJson(data).toString())
            emit(data)
        } catch (e: Exception) {
            if (e is HttpException){
                emit(EndPointResult.Error(e.code(),message = e.message.toString()))
            }else {
                emit(EndPointResult.Error(message = "Unable to communicate with the server."))
                e.printStackTrace()
            }
        }
    }

    abstract suspend fun getData(params: P?): EndPointResult<R>

    suspend operator fun invoke(params: P) = getResponse(params)

    suspend operator fun invoke() = getResponse(null)
}
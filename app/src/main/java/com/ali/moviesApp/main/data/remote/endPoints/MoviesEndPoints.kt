

package com.ali.moviesApp.main.data.remote.endPoints

import com.ali.moviesApp.main.data.remote.dtos.MovieDTO
import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.ali.moviesApp.main.data.remote.dtos.ResponseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesEndPoints {
    @GET("api/v1/movies")
    suspend fun getMovies(): Response<List<MovieDTO>>

    @GET("api/v1/movies/{id}")
    suspend fun getMovie(@Path("id") id : Int): Response<MovieDTO>
}
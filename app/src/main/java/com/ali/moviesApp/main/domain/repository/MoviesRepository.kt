package com.ali.moviesApp.main.domain.repository

import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel

interface MoviesRepository {
    suspend fun getMovies() : EndPointResult<List<MovieUiModel>>
    suspend fun getMovie(id : Int) : EndPointResult<MovieUiModel>
    suspend fun addToFavorites(model: MovieUiModel, isFavorite: Boolean)
    suspend fun getAllFavoriteMovies(): List<MovieUiModel>

}
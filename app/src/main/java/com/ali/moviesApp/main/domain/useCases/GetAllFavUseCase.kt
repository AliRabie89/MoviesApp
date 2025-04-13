package com.ali.moviesApp.main.domain.useCases

import com.ali.moviesApp.main.base.BaseUseCase
import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.ali.moviesApp.main.domain.repository.MoviesRepository
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel

class GetAllFavUseCase(private val moviesRepository: MoviesRepository) : BaseUseCase<Unit, List<MovieUiModel>>() {

    override suspend fun getData(params: Unit?): EndPointResult<List<MovieUiModel>> {
        return try {
            // Call the repository to get all favorite movies
            val favoriteMovies = moviesRepository.getAllFavoriteMovies()
            EndPointResult.Success(favoriteMovies)
        } catch (e: Exception) {
            // In case of an error, return an error result
            EndPointResult.Error(500, e.message ?: "An error occurred while fetching favorite movies")
        }
    }
}

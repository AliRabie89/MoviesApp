package com.ali.moviesApp.main.domain.useCases

import com.ali.moviesApp.main.base.BaseUseCase
import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.ali.moviesApp.main.domain.repository.MoviesRepository
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel

class AddToFavUseCase(private val moviesRepository: MoviesRepository) : BaseUseCase<AddToFavUseCase.AddToFavParams, Unit>() {

    override suspend fun getData(params: AddToFavParams?): EndPointResult<Unit> {
        // Ensure that params is not null
        val movie = params?.moveUiModel
        val isFavorite = params?.isFavorite
        return try {
            // Call repository method to update the favorite status
            moviesRepository.addToFavorites(movie!!, isFavorite!!)
            // Return success result
            EndPointResult.Success(Unit)
        } catch (e: Exception) {
            // Return error result in case of exception
            EndPointResult.Error(500, e.message ?: "An error occurred while updating favorite status")
        }
    }

    data class AddToFavParams(val moveUiModel: MovieUiModel, val isFavorite: Boolean)
}
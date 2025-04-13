package com.ali.moviesApp.main.domain.useCases

import com.ali.moviesApp.main.base.BaseUseCase
import com.ali.moviesApp.main.domain.repository.MoviesRepository
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel

class GetMovieUseCase(private val moviesRepository: MoviesRepository) : BaseUseCase<Int, MovieUiModel>(){
    override suspend fun getData(params: Int?) = moviesRepository.getMovie(params!!)
}
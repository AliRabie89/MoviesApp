package com.ali.moviesApp.main.domain.useCases

import com.ali.moviesApp.main.base.BaseUseCase
import com.ali.moviesApp.main.domain.repository.MoviesRepository
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel

class GetMoviesUseCase(private val moviesRepository: MoviesRepository) : BaseUseCase<HashMap<String, Int>, List<MovieUiModel>>(){
    override suspend fun getData(params: HashMap<String, Int>?) = moviesRepository.getMovies()
}
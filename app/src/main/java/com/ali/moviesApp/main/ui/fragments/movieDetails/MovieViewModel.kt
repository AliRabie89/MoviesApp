

package com.ali.moviesApp.main.ui.fragments.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel
import com.ali.moviesApp.main.domain.useCases.AddToFavUseCase
import com.ali.moviesApp.main.domain.useCases.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val addToFavUseCase: AddToFavUseCase
) : ViewModel() {
    private val _movieFlow: MutableStateFlow<EndPointResult<MovieUiModel>> =
        MutableStateFlow(EndPointResult.Loading(false))
    val movieFlow : StateFlow<EndPointResult<MovieUiModel>> = _movieFlow

    fun getMovie (id : Int){
        viewModelScope.launch {
            _movieFlow.emit(EndPointResult.Loading(true))
            getMovieUseCase(id).collectLatest {
                _movieFlow.emit(EndPointResult.Loading(false))
                _movieFlow.emit(it)
            }
        }
    }

    fun addMovieToFavorites(movieUiModel: MovieUiModel, isFavorite: Boolean) {
        viewModelScope.launch {
            val result = addToFavUseCase.getData(AddToFavUseCase.AddToFavParams(movieUiModel, isFavorite))
            when (result) {
                is EndPointResult.Success -> {}
                is EndPointResult.Error -> {}
                else -> {}
            }
        }
    }
}


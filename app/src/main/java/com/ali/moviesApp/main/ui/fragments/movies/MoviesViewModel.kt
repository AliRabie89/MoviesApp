

package com.ali.moviesApp.main.ui.fragments.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel
import com.ali.moviesApp.main.domain.useCases.AddToFavUseCase
import com.ali.moviesApp.main.domain.useCases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val addToFavUseCase: AddToFavUseCase
) : ViewModel() {
    private val _moviesFlow: MutableStateFlow<EndPointResult<List<MovieUiModel>>> =
        MutableStateFlow(EndPointResult.Loading(false))
    val moviesFlow : StateFlow<EndPointResult<List<MovieUiModel>>> = _moviesFlow


    fun getMovies (){
        val request = HashMap<String,Int>()
        viewModelScope.launch {
            _moviesFlow.emit(EndPointResult.Loading(true))
            getMoviesUseCase(request).collectLatest {
                _moviesFlow.emit(EndPointResult.Loading(false))
                _moviesFlow.emit(it)
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


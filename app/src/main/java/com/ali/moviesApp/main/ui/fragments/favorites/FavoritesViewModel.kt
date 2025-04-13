

package com.ali.moviesApp.main.ui.fragments.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel
import com.ali.moviesApp.main.domain.useCases.AddToFavUseCase
import com.ali.moviesApp.main.domain.useCases.GetAllFavUseCase
import com.ali.moviesApp.main.domain.useCases.GetMovieUseCase
import com.ali.moviesApp.main.domain.useCases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavUseCase: GetAllFavUseCase,
    private val addToFavUseCase: AddToFavUseCase
) : ViewModel() {
    private val _moviesFlow: MutableStateFlow<EndPointResult<List<MovieUiModel>>> =
        MutableStateFlow(EndPointResult.Loading(false))
    val moviesFlow : StateFlow<EndPointResult<List<MovieUiModel>>> = _moviesFlow

    fun getFavorites (){
        viewModelScope.launch {
            getAllFavUseCase().collectLatest {
                _moviesFlow.emit(it)
            }
        }
    }


    fun removeMovieToFavorites(movieUiModel: MovieUiModel, isFavorite: Boolean) {
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

